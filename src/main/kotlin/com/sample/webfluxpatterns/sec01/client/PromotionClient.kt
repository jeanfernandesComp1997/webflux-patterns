package com.sample.webfluxpatterns.sec01.client

import com.sample.webfluxpatterns.sec01.dto.PromotionResponse
import reactor.core.publisher.Mono

interface PromotionClient {

    fun retrievePromotion(id: Int): Mono<PromotionResponse>
}