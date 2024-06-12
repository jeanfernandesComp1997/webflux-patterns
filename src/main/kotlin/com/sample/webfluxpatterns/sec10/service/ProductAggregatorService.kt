package com.sample.webfluxpatterns.sec10.service

import com.sample.webfluxpatterns.sec10.dto.ProductAggregate
import reactor.core.publisher.Mono

interface ProductAggregatorService {

    fun aggregate(id: Int): Mono<ProductAggregate>
}