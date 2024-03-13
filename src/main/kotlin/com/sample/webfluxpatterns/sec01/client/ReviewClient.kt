package com.sample.webfluxpatterns.sec01.client

import com.sample.webfluxpatterns.sec01.dto.ReviewResponse
import reactor.core.publisher.Mono

interface ReviewClient {

    fun retrieveReviews(id: Int): Mono<List<ReviewResponse>>
}