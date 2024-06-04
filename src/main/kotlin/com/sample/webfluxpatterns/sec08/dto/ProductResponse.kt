package com.sample.webfluxpatterns.sec08.dto

data class ProductResponse(
    val id: Int,
    val category: String,
    val description: String,
    val price: Int
)