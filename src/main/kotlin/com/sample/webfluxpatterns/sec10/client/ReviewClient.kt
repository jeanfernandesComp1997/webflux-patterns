package com.sample.webfluxpatterns.sec10.client

import com.sample.webfluxpatterns.sec10.dto.ReviewResponse
import reactor.core.publisher.Mono

interface ReviewClient {

    fun retrieveReviews(id: Int): Mono<List<ReviewResponse>>
}