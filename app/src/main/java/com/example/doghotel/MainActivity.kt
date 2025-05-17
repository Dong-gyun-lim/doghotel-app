package com.example.doghotel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doghotel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hotelList = listOf(
            Hotel("남남 강아지 호텔", "★평점:4.7", "거리: 3KM 이내", "35000원", android.R.drawable.ic_menu_report_image),
            Hotel("펑펑 강아지 호텔", "★평점:4.4", "거리: 2KM 이내", "40000원", android.R.drawable.ic_menu_report_image),
            Hotel("룽룽 강아지 호텔", "★평점:4.9", "거리: 1KM 이내", "45000원", android.R.drawable.ic_menu_report_image)
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = HotelAdapter(this, hotelList)
    }
}
