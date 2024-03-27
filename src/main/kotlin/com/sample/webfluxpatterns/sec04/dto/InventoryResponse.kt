package com.sample.webfluxpatterns.sec04.dto

import java.util.UUID

data class InventoryResponse(
    val inventoryId: UUID,
    val productId: Int,
    val quantity: Int,
    val remainingQuantity: Int?,
    val status: Status
)
