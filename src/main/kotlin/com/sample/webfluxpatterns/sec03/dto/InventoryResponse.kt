package com.sample.webfluxpatterns.sec03.dto

data class InventoryResponse(
    val productId: Int,
    val quantity: Int,
    val remainingQuantity: Int?,
    val status: Status
)
