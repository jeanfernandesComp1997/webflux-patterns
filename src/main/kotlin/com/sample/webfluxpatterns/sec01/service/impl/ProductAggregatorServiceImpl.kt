package com.sample.webfluxpatterns.sec01.service.impl

import com.sample.webfluxpatterns.sec01.client.ProductClient
import com.sample.webfluxpatterns.sec01.client.PromotionClient
import com.sample.webfluxpatterns.sec01.client.ReviewClient
import com.sample.webfluxpatterns.sec01.dto.*
import com.sample.webfluxpatterns.sec01.service.ProductAggregatorService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ProductAggregatorServiceImpl(
    private val productClient: ProductClient,
    private val promotionClient: PromotionClient,
    private val reviewClient: ReviewClient
) : ProductAggregatorService {

    override fun aggregate(id: Int): Mono<ProductAggregate> {
        return Mono.zip(
            productClient.retrieveProduct(id),
            promotionClient.retrievePromotion(id),
            reviewClient.retrieveReviews(id)
        )
            .map {
                toDto(it.t1, it.t2, it.t3)
            }
    }

    private fun toDto(
        productResponse: ProductResponse,
        promotionResponse: PromotionResponse,
        reviewResponse: List<ReviewResponse>
    ): ProductAggregate {
        val amountSaved = productResponse.price * promotionResponse.discount / 100
        val discountedPrice = productResponse.price - amountSaved
        val price = Price(
            listPrice = productResponse.price,
            discount = promotionResponse.discount,
            discountedPrice = discountedPrice,
            amountSaved = amountSaved,
            endDate = promotionResponse.endDate
        )
        return ProductAggregate(
            id = productResponse.id,
            category = productResponse.category,
            description = productResponse.description,
            price = price,
            reviews = reviewResponse
        )
    }
}