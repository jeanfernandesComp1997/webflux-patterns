package com.sample.webfluxpatterns.sec09.client.impl

import com.sample.webfluxpatterns.sec09.client.ReviewClient
import com.sample.webfluxpatterns.sec09.dto.ReviewResponse
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class ReviewClientImpl(
    @Value("\${sec09.review.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) : ReviewClient {

    private var client: WebClient = webClientBuilder
        .baseUrl(baseUrl)
        .build()

    @RateLimiter(name = "review-service", fallbackMethod = "fallback")
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
    }

    fun fallback(id: Int, ex: Throwable): Mono<List<ReviewResponse>> {
        return Mono.just(emptyList())
    }
}