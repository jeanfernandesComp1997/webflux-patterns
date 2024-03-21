package com.sample.webfluxpatterns.sec03.client.impl

import com.sample.webfluxpatterns.sec03.client.InventoryClient
import com.sample.webfluxpatterns.sec03.dto.InventoryRequest
import com.sample.webfluxpatterns.sec03.dto.InventoryResponse
import com.sample.webfluxpatterns.sec03.dto.Status
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import reactor.core.publisher.Mono

@Component
class InventoryClientImpl(
    @Value("\${sec03.inventory.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) : InventoryClient {

    companion object {
        private const val DEDUCT = "deduct"
        private const val RESTORE = "restore"
    }

    private var client: WebClient = webClientBuilder
        .baseUrl(baseUrl)
        .build()

    override fun deduct(inventoryRequest: InventoryRequest): Mono<InventoryResponse> {
        return callService(DEDUCT, inventoryRequest)
    }

    override fun restore(inventoryRequest: InventoryRequest): Mono<InventoryResponse> {
        return callService(RESTORE, inventoryRequest)
    }

    private fun callService(endpoint: String, inventoryRequest: InventoryRequest): Mono<InventoryResponse> {
        return client
            .post()
            .uri(endpoint)
            .bodyValue(inventoryRequest)
            .retrieve()
            .bodyToMono(InventoryResponse::class.java)
            .onErrorReturn(buildErrorResponse(inventoryRequest))
    }

    private suspend fun callServiceSuspend(endpoint: String, inventoryRequest: InventoryRequest): InventoryResponse {
        return client
            .post()
            .uri(endpoint)
            .bodyValue(inventoryRequest)
            .retrieve()
            .awaitBody()
    }

    private fun buildErrorResponse(inventoryRequest: InventoryRequest): InventoryResponse {
        return InventoryResponse(
            productId = inventoryRequest.productId,
            quantity = inventoryRequest.quantity,
            remainingQuantity = null,
            status = Status.FAILED
        )
    }
}