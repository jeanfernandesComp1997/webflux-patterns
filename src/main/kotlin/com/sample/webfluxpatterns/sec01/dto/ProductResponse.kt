package com.sample.webfluxpatterns.sec01.dto

data class ProductResponse(
    val id: Int,
    val category: String,
    val description: String,
    val price: Int
)