package com.sample.webfluxpatterns.sec03.dto

import java.util.UUID

data class ShippingResponse(
    val orderId: UUID,
    val quantity: Int,
    val status: Status,
    val expectedDelivery: String?,
    val address: Address?
)
