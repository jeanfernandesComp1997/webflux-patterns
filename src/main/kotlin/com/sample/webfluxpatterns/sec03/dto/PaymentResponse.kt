package com.sample.webfluxpatterns.sec03.dto

data class PaymentResponse(
    val userId: Int,
    val name: String?,
    val balance: Int,
    val status: Status
)
