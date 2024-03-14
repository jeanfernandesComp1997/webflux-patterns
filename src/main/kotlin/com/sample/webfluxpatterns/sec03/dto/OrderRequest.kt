package com.sample.webfluxpatterns.sec03.dto

data class OrderRequest(
    val userId: Int,
    val productId: Int,
    val quantity: Int
)