package com.sample.webfluxpatterns.sec06.client

import com.sample.webfluxpatterns.sec06.dto.ProductResponse
import reactor.core.publisher.Mono

interface ProductClient {

    fun retrieveProduct(id: Int): Mono<ProductResponse>
}