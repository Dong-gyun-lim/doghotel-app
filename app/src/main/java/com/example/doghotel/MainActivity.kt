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
            Hotel("남남 강아지 호텔", "★평점:4.7", "거리: 3KM 이내", "35000원", android.R.drawable.ic_menu_report_image,
                "24시간 돌봄 · 애견 스파 · 건강 간식 제공", "서울시 강남구 도곡로 123"),
            Hotel("펑펑 강아지 호텔", "★평점:4.4", "거리: 2KM 이내", "40000원", android.R.drawable.ic_menu_report_image,
                "실시간 CCTV · 무료 목욕권 제공", "서울시 송파구 석촌로 222"),
            Hotel("룽룽 강아지 호텔", "★평점:4.9", "거리: 1KM 이내", "45000원", android.R.drawable.ic_menu_report_image,
                "전문 트레이너 상주 · 유치원 연계 서비스", "서울시 마포구 연남로 15"),
            Hotel("멍스타 펫텔", "★평점:4.8", "거리: 2.5KM", "37000원", android.R.drawable.ic_menu_report_image,
                "프리미엄 간식 · 대형 운동장", "서울시 용산구 이태원로 55"),
            Hotel("댕댕하우스", "★평점:4.5", "거리: 3.2KM", "33000원", android.R.drawable.ic_menu_report_image,
                "초보 반려인을 위한 상담 제공", "서울시 관악구 봉천로 77"),
            Hotel("애견빌리지", "★평점:4.3", "거리: 1.8KM", "36000원", android.R.drawable.ic_menu_report_image,
                "전용 수면공간 · 맞춤형 식단", "서울시 종로구 사직로 101"),
            Hotel("멍푸드 호텔", "★평점:4.6", "거리: 2.2KM", "39000원", android.R.drawable.ic_menu_report_image,
                "식이민감 반려견을 위한 식단 관리", "서울시 성동구 왕십리로 88"),
            Hotel("강쥐랜드", "★평점:4.2", "거리: 4.0KM", "31000원", android.R.drawable.ic_menu_report_image,
                "대형견 전용 객실 보유 · 야외 운동장", "서울시 은평구 불광로 19"),
            Hotel("소소한 멍라이프", "★평점:4.9", "거리: 1.5KM", "47000원", android.R.drawable.ic_menu_report_image,
                "1:1 맞춤 케어 · 셀프 목욕실 제공", "서울시 서대문구 연희로 35"),
            Hotel("위글위글 호텔", "★평점:4.7", "거리: 2.7KM", "42000원", android.R.drawable.ic_menu_report_image,
                "소형견 전용 힐링 공간 · 조용한 환경", "서울시 동작구 흑석로 99")
        )


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = HotelAdapter(this, hotelList)
    }
}
