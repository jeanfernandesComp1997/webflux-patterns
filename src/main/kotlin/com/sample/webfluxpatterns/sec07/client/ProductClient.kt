package com.sample.webfluxpatterns.sec07.client

import com.sample.webfluxpatterns.sec07.dto.ProductResponse
import reactor.core.publisher.Mono

interface ProductClient {

    fun retrieveProduct(id: Int): Mono<ProductResponse>
}