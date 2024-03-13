package com.sample.webfluxpatterns.sec01.client

import com.sample.webfluxpatterns.sec01.dto.ProductResponse
import reactor.core.publisher.Mono

interface ProductClient {

    fun retrieveProduct(id: Int): Mono<ProductResponse>
}