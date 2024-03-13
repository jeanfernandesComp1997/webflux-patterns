package com.sample.webfluxpatterns.sec01.client.impl

import com.sample.webfluxpatterns.sec01.client.ReviewClient
import com.sample.webfluxpatterns.sec01.dto.ReviewResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class ReviewClientImpl(
    @Value("\${sec01.review.service}")
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
            .bodyToFlux(ReviewResponse::class.java)
            .collectList()
            .onErrorReturn(listOf<ReviewResponse>())
    }
}