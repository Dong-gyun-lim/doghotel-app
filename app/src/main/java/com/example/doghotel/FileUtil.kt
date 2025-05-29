package com.example.doghotel

import android.content.Context
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader

class FileUtil {

    fun loadHotelsFromFile(context: Context): List<Hotel> {
        val hotelList = mutableListOf<Hotel>()
        try {
            val fileName = "hotels.txt"
            val inputStream = context.openFileInput(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                val tokens = line!!.split("|")
                if (tokens.size >= 7) {
                    val hotel = Hotel(
                        name = tokens[0],
                        rating = tokens[1],
                        distance = tokens[2],
                        price = tokens[3],
                        imageResId = android.R.drawable.ic_menu_report_image,
                        description = tokens[5],
                        address = tokens[6]
                    )
                    hotelList.add(hotel)
                }
            }

            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return hotelList
    }

    fun loadReservationsFromFile(context: Context): List<Reservation> {
        val list = mutableListOf<Reservation>()
        try {
            val file = File(context.filesDir, "new_reservations.txt")
            if (!file.exists()) return list

            val input = context.openFileInput("new_reservations.txt")
            val reader = BufferedReader(InputStreamReader(input))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                val tokens = line!!.split("|")
                if (tokens.size >= 6) {
                    list.add(
                        Reservation(
                            id = tokens[0],
                            guestName = tokens[1],
                            petName = tokens[2],
                            checkInDate = tokens[3],
                            status = tokens[4],
                            notes = tokens[5]
                        )
                    )
                }
            }

            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun loadReviewsFromFile(context: Context): List<Review> {
        val list = mutableListOf<Review>()
        try {
            val input = context.openFileInput("reviews.txt")
            val reader = BufferedReader(InputStreamReader(input))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                val tokens = line!!.split("|")
                if (tokens.size >= 4) {
                    list.add(
                        Review(
                            guestName = tokens[0],
                            rating = tokens[1].toFloatOrNull() ?: 0f,
                            comment = tokens[2],
                            isReported = tokens[3].toBooleanStrictOrNull() ?: false
                        )
                    )
                }
            }

            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun loadCheckinsFromFile(context: Context): List<String> {
        val list = mutableListOf<String>()
        try {
            val input = context.openFileInput("checkins.txt")
            val reader = BufferedReader(InputStreamReader(input))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                list.add(line!!)
            }

            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun loadDogUsersFromFile(context: Context): List<DogUser> {
        val list = mutableListOf<DogUser>()
        try {
            val input = context.openFileInput("users.txt")
            val reader = BufferedReader(InputStreamReader(input))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                val tokens = line!!.split("|")
                if (tokens.size == 3) {
                    list.add(
                        DogUser(
                            ownerName = tokens[0],
                            dogName = tokens[1],
                            dogAge = tokens[2].toIntOrNull() ?: 0
                        )
                    )
                }
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return list
    }

    fun copyFileFromAssets(context: Context, fileName: String) {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) {
            context.assets.open(fileName).use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }
    }

    fun copyHotelFileFromAssets(context: Context) {
        val fileName = "hotels.txt"
        val file = File(context.filesDir, fileName)
        if (!file.exists()) {
            context.assets.open(fileName).use { inputStream ->
                FileOutputStream(file).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        }
    }
}