package com.sample.webfluxpatterns.sec07.service

import com.sample.webfluxpatterns.sec07.dto.ProductAggregate
import reactor.core.publisher.Mono

interface ProductAggregatorService {

    fun aggregate(id: Int): Mono<ProductAggregate>
}