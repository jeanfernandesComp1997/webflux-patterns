package com.sample.webfluxpatterns.sec04.client

import com.sample.webfluxpatterns.sec04.dto.InventoryRequest
import com.sample.webfluxpatterns.sec04.dto.InventoryResponse
import reactor.core.publisher.Mono

interface InventoryClient {

    fun deduct(inventoryRequest: InventoryRequest): Mono<InventoryResponse>

    fun restore(inventoryRequest: InventoryRequest): Mono<InventoryResponse>
}