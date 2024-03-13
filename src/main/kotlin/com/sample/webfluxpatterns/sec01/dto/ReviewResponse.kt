package com.sample.webfluxpatterns.sec01.dto

data class ReviewResponse(
    val id: Int,
    val user: String,
    val rating: Int,
    val comment: String
)