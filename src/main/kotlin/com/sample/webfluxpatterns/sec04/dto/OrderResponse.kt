package com.sample.webfluxpatterns.sec04.dto

import java.util.UUID

data class OrderResponse(
    val userId: Int,
    val productId: Int,
    val orderId: UUID,
    val status: Status,
    val shippingAddress: Address?,
    val expectedDelivery: String?
)
