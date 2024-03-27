package com.sample.webfluxpatterns.sec04.dto

import java.util.UUID

data class PaymentResponse(
    val paymentId: UUID,
    val userId: Int,
    val name: String?,
    val balance: Int,
    val status: Status
)
