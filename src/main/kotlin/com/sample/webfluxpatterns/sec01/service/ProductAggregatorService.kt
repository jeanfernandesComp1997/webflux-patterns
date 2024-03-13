package com.sample.webfluxpatterns.sec01.service

import com.sample.webfluxpatterns.sec01.dto.ProductAggregate
import reactor.core.publisher.Mono

interface ProductAggregatorService {

    fun aggregate(id: Int): Mono<ProductAggregate>
}