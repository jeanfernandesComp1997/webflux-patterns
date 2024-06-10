package com.sample.webfluxpatterns.sec09.service

import com.sample.webfluxpatterns.sec09.dto.ProductAggregate
import reactor.core.publisher.Mono

interface ProductAggregatorService {

    fun aggregate(id: Int): Mono<ProductAggregate>
}