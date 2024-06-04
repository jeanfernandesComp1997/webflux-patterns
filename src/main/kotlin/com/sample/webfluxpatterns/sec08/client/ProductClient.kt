package com.sample.webfluxpatterns.sec08.client

import com.sample.webfluxpatterns.sec08.dto.ProductResponse
import reactor.core.publisher.Mono

interface ProductClient {

    fun retrieveProduct(id: Int): Mono<ProductResponse>
}