package com.sample.webfluxpatterns.sec08.service

import com.sample.webfluxpatterns.sec08.dto.ProductAggregate
import reactor.core.publisher.Mono

interface ProductAggregatorService {

    fun aggregate(id: Int): Mono<ProductAggregate>
}