package com.sample.webfluxpatterns.sec01.client.impl

import com.sample.webfluxpatterns.sec01.client.PromotionClient
import com.sample.webfluxpatterns.sec01.dto.PromotionResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.LocalDate

@Component
class PromotionClientImpl(
    @Value("\${sec01.promotion.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) : PromotionClient {

    private var client: WebClient = webClientBuilder
        .baseUrl(baseUrl)
        .build()

    private val noPromotion: PromotionResponse = PromotionResponse(
        id = -1,
        type = "no promotion",
        discount = 0.0,
        endDate = LocalDate.now()
    )

    override fun retrievePromotion(id: Int): Mono<PromotionResponse> {
        return client
            .get()
            .uri("{id}", id)
            .retrieve()
            .bodyToMono(PromotionResponse::class.java)
            .onErrorReturn(noPromotion)
    }
}