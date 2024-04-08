package com.sample.webfluxpatterns.sec06.dto

data class ProductAggregate(
    val id: Int,
    val category: String,
    val description: String,
    val reviews: List<ReviewResponse>
)