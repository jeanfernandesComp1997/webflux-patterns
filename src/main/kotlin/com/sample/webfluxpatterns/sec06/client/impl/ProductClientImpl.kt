package com.sample.webfluxpatterns.sec06.client.impl

import com.sample.webfluxpatterns.sec06.client.ProductClient
import com.sample.webfluxpatterns.sec06.dto.ProductResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class ProductClientImpl(
    @Value("\${sec06.product.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) : ProductClient {

    private var client: WebClient = webClientBuilder
        .baseUrl(baseUrl)
        .build()

    override fun retrieveProduct(id: Int): Mono<ProductResponse> {
        return client
            .get()
            .uri("{id}", id)
            .retrieve()
            .bodyToMono(ProductResponse::class.java)
            .onErrorResume { Mono.empty() }
    }
}