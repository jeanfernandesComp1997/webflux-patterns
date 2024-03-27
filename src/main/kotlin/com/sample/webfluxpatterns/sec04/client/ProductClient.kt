package com.sample.webfluxpatterns.sec04.client

import com.sample.webfluxpatterns.sec04.dto.Product
import reactor.core.publisher.Mono

interface ProductClient {

    fun retrieveProduct(id: Int): Mono<Product>
}