package com.sample.webfluxpatterns.sec08.client.impl

import com.sample.webfluxpatterns.sec08.client.ReviewClient
import com.sample.webfluxpatterns.sec08.dto.ReviewResponse
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.Duration

@Component
class ReviewClientImpl(
    @Value("\${sec08.review.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) : ReviewClient {

    private var client: WebClient = webClientBuilder
        .baseUrl(baseUrl)
        .build()

    @CircuitBreaker(name = "review-service", fallbackMethod = "fallbackReview")
    override fun retrieveReviews(id: Int): Mono<List<ReviewResponse>> {
        return client
            .get()
            .uri("{id}", id)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) {
                Mono.empty()
            }
            .bodyToFlux(ReviewResponse::class.java)
            .collectList()
            .retry(5)
            .timeout(Duration.ofMillis(300))
    }

    fun fallbackReview(id: Int, ex: Throwable): Mono<List<ReviewResponse>> {
        println("Fallback method called: ${ex.message}")
        return Mono.just(listOf())
    }
}