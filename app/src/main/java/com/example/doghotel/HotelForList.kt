package com.example.doghotel

data class HotelForList(
    val name: String,
    val rating: String,     // "★ 4.8"
    val distance: String,   // "거리: 2.5KM"
    val price: String,      // "₩25,000원~"
    val imageResId: Int,
    val description: String,
    val address: String
)
