package com.sample.webfluxpatterns.sec08.controller

import com.sample.webfluxpatterns.sec08.dto.ProductAggregate
import com.sample.webfluxpatterns.sec08.service.ProductAggregatorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("sec08")
class AggregatorController(
    private val productAggregatorService: ProductAggregatorService
) {

    @GetMapping("products/{id}")
    fun retrieveProduct(@PathVariable id: Int): Mono<ResponseEntity<ProductAggregate>> {
        return productAggregatorService
            .aggregate(id)
            .map { ResponseEntity.ok(it) }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }
}