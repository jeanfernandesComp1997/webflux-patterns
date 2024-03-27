package com.sample.webfluxpatterns.sec04.dto

import java.util.UUID

data class ShippingRequest(
    val inventoryId: UUID,
    val quantity: Int,
    val userId: Int
)
