package com.sample.webfluxpatterns.sec03.client

import com.sample.webfluxpatterns.sec03.dto.InventoryRequest
import com.sample.webfluxpatterns.sec03.dto.InventoryResponse
import reactor.core.publisher.Mono

interface InventoryClient {

    fun deduct(inventoryRequest: InventoryRequest): Mono<InventoryResponse>

    fun refund(inventoryRequest: InventoryRequest): Mono<InventoryResponse>
}