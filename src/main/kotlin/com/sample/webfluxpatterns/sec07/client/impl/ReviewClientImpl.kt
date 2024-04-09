package com.sample.webfluxpatterns.sec07.client.impl

import com.sample.webfluxpatterns.sec07.client.ReviewClient
import com.sample.webfluxpatterns.sec07.dto.ReviewResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import reactor.util.retry.Retry
import java.time.Duration

@Component
class ReviewClientImpl(
    @Value("\${sec07.review.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) : ReviewClient {

    private var client: WebClient = webClientBuilder
        .baseUrl(baseUrl)
        .build()

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
            .timeout(Duration.ofMillis(300))
            .retryWhen(
                Retry.fixedDelay(5, Duration.ofSeconds(1))
                    .filter { error ->
                        when (error) {
                            is WebClientResponseException.InternalServerError -> true
                            is WebClientResponseException.GatewayTimeout -> true
                            is WebClientResponseException.ServiceUnavailable -> true
                            is WebClientResponseException.BadGateway -> true
                            else -> false
                        }
                    }
            )

            .onErrorReturn(listOf<ReviewResponse>())
    }
}