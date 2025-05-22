package com.example.doghotel

import android.os.Parcel
import android.os.Parcelable

data class Reservation(
    val id: String,
    val guestName: String,
    val petName: String,
    val checkInDate: String,
    val status: String,
    val notes: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(guestName)
        parcel.writeString(petName)
        parcel.writeString(checkInDate)
        parcel.writeString(status)
        parcel.writeString(notes)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Reservation> {
        override fun createFromParcel(parcel: Parcel): Reservation {
            return Reservation(parcel)
        }

        override fun newArray(size: Int): Array<Reservation?> {
            return arrayOfNulls(size)
        }
    }
}
