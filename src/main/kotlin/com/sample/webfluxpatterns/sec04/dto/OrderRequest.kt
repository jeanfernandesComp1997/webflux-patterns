package com.sample.webfluxpatterns.sec04.dto

data class OrderRequest(
    val userId: Int,
    val productId: Int,
    val quantity: Int
)