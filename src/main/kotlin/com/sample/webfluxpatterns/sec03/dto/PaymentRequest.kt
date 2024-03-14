package com.sample.webfluxpatterns.sec03.dto

import java.util.UUID

data class PaymentRequest(
    val userId: Int,
    val amount: Int,
    val orderId: UUID
)
