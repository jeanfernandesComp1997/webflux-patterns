package com.sample.webfluxpatterns.sec09.service.impl

import com.sample.webfluxpatterns.sec09.client.ProductClient
import com.sample.webfluxpatterns.sec09.client.ReviewClient
import com.sample.webfluxpatterns.sec09.dto.ProductAggregate
import com.sample.webfluxpatterns.sec09.dto.ProductResponse
import com.sample.webfluxpatterns.sec09.dto.ReviewResponse
import com.sample.webfluxpatterns.sec09.service.ProductAggregatorService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ProductAggregatorServiceImpl(
    private val productClient: ProductClient,
    private val reviewClient: ReviewClient
) : ProductAggregatorService {

    override fun aggregate(id: Int): Mono<ProductAggregate> {
        return Mono.zip(
            productClient.retrieveProduct(id),
            reviewClient.retrieveReviews(id)
        )
            .map {
                toDto(it.t1, it.t2)
            }
    }

    private fun toDto(
        productResponse: ProductResponse,
        reviewResponse: List<ReviewResponse>
    ): ProductAggregate {
        return ProductAggregate(
            id = productResponse.id,
            category = productResponse.category,
            description = productResponse.description,
            reviews = reviewResponse
        )
    }
}