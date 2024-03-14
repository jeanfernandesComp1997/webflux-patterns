package com.sample.webfluxpatterns.sec03.dto

import java.util.UUID

data class ShippingRequest(
    val quantity: Int,
    val userId: Int,
    val orderId: UUID
)
