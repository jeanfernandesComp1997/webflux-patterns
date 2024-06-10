package com.sample.webfluxpatterns.sec09.client

import com.sample.webfluxpatterns.sec09.dto.ReviewResponse
import reactor.core.publisher.Mono

interface ReviewClient {

    fun retrieveReviews(id: Int): Mono<List<ReviewResponse>>
}