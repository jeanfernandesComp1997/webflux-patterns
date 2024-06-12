package com.sample.webfluxpatterns.sec10.client

import com.sample.webfluxpatterns.sec10.dto.ProductResponse
import reactor.core.publisher.Mono

interface ProductClient {

    fun retrieveProduct(id: Int): Mono<ProductResponse>
}