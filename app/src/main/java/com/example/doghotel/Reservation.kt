package com.example.doghotel

data class Reservation(
    val id: String,
    val guestName: String,
    val petName: String,
    val checkInDate: String,
    val status: String, // 예약완료 / 입실중 / 완료 / 취소
    val notes: String
)