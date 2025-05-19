package com.example.doghotel

data class PriceRule(
    val maxWeight: Double,
    val weekdayPrice: Int,
    val weekendPrice: Int = weekdayPrice
)
