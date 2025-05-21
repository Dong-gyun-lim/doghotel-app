package com.example.doghotel

data class Review(
    val guestName: String,
    val rating: Float,
    val comment: String,
    val isReported: Boolean
)
