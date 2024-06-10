package com.sample.webfluxpatterns.sec09.client

import com.sample.webfluxpatterns.sec09.dto.ProductResponse
import reactor.core.publisher.Mono

interface ProductClient {

    fun retrieveProduct(id: Int): Mono<ProductResponse>
}