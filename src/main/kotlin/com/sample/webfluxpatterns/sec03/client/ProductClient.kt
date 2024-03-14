package com.sample.webfluxpatterns.sec03.client

import com.sample.webfluxpatterns.sec03.dto.Product
import reactor.core.publisher.Mono

interface ProductClient {

    fun retrieveProduct(id: Int): Mono<Product>
}