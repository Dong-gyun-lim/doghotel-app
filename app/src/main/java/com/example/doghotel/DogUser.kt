package com.example.doghotel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogUser(
    val ownerName: String,
    val dogName: String,
    val dogAge: Int
) : Parcelable
