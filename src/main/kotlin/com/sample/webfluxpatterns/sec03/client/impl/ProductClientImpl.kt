package com.sample.webfluxpatterns.sec03.client.impl

import com.sample.webfluxpatterns.sec03.client.ProductClient
import com.sample.webfluxpatterns.sec03.dto.Product
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class ProductClientImpl(
    @Value("\${sec03.product.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) : ProductClient {

    private var client: WebClient = webClientBuilder
        .baseUrl(baseUrl)
        .build()

    override fun retrieveProduct(id: Int): Mono<Product> {
        return client
            .get()
            .uri("{id}", id)
            .retrieve()
            .bodyToMono(Product::class.java)
            .onErrorResume { Mono.empty() }
    }
}