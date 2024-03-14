package com.sample.webfluxpatterns.sec03.dto

import java.util.UUID

data class InventoryRequest(
    val orderId: UUID,
    val productId: Int,
    val quantity: Int
)
