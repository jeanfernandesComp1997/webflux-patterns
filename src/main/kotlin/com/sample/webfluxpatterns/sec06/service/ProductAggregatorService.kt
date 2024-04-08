package com.sample.webfluxpatterns.sec06.service

import com.sample.webfluxpatterns.sec06.dto.ProductAggregate
import reactor.core.publisher.Mono

interface ProductAggregatorService {

    fun aggregate(id: Int): Mono<ProductAggregate>
}