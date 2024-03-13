package com.sample.webfluxpatterns.sec01.dto

data class ProductAggregate(
    val id: Int,
    val category: String,
    val description: String,
    val price: Price,
    val reviews: List<ReviewResponse>
)