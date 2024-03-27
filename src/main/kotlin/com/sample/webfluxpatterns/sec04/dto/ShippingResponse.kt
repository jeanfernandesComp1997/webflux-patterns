package com.sample.webfluxpatterns.sec04.dto

import java.util.UUID

data class ShippingResponse(
    val shippingId: UUID,
    val quantity: Int,
    val status: Status,
    val expectedDelivery: String?,
    val address: Address?
)
