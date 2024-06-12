package com.sample.webfluxpatterns.sec10.client.impl

import com.sample.webfluxpatterns.sec10.client.ReviewClient
import com.sample.webfluxpatterns.sec10.dto.ReviewResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class ReviewClientImpl(
    @Value("\${sec10.review.service}")
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
    }
}