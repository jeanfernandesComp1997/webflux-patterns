package com.sample.webfluxpatterns.sec04.dto

import java.util.UUID

data class InventoryRequest(
    val paymentId: UUID,
    val productId: Int,
    val quantity: Int
)
