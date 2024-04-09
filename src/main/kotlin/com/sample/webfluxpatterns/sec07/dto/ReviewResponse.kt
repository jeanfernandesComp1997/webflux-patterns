package com.sample.webfluxpatterns.sec07.dto

data class ReviewResponse(
    val id: Int,
    val user: String,
    val rating: Int,
    val comment: String
)