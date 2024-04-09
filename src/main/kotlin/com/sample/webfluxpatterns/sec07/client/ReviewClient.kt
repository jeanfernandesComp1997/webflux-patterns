package com.sample.webfluxpatterns.sec07.client

import com.sample.webfluxpatterns.sec07.dto.ReviewResponse
import reactor.core.publisher.Mono

interface ReviewClient {

    fun retrieveReviews(id: Int): Mono<List<ReviewResponse>>
}