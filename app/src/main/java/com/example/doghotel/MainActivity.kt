package com.example.doghotel

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doghotel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var hotelList: MutableList<Hotel>
    private lateinit var adapter: HotelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 호텔 리스트 + 이미지 연결
        hotelList = mutableListOf(
            Hotel("냠냠 강아지 호텔", "★평점:4.7", "거리: 3KM 이내", "20000원", R.drawable.pet1, "24시간 돌봄 · 애견 스파 · 건강 간식 제공", "서울시 강남구 도곡로 123"),
            Hotel("펑펑 강아지 호텔", "★평점:4.4", "거리: 2KM 이내", "30000원", R.drawable.pet2, "실시간 CCTV · 무료 목욕권 제공", "서울시 송파구 석촌로 222"),
            Hotel("룽룽 강아지 호텔", "★평점:4.9", "거리: 1KM 이내", "30000원", R.drawable.pet3, "전문 트레이너 상주 · 유치원 연계 서비스", "서울시 마포구 연남로 15"),
            Hotel("멍스타 펫텔", "★평점:4.8", "거리: 2.5KM", "30000원", R.drawable.pet4, "프리미엄 간식 · 대형 운동장", "서울시 용산구 이태원로 55"),
            Hotel("댕댕하우스", "★평점:4.5", "거리: 3.2KM", "30000원", R.drawable.pet5, "초보 반려인을 위한 상담 제공", "서울시 관악구 봉천로 77"),
            Hotel("애견빌리지", "★평점:4.3", "거리: 1.8KM", "28000원", R.drawable.pet6, "전용 수면공간 · 맞춤형 식단", "서울시 종로구 사직로 101"),
            Hotel("멍푸드 호텔", "★평점:4.6", "거리: 2.2KM", "31000원", R.drawable.pet7, "식이민감 반려견을 위한 식단 관리", "서울시 성동구 왕십리로 88"),
            Hotel("강쥐랜드", "★평점:4.2", "거리: 4.0KM", "25000원", R.drawable.pet8, "대형견 전용 객실 보유 · 야외 운동장", "서울시 은평구 불광로 19"),
            Hotel("소소한 멍라이프", "★평점:4.9", "거리: 1.5KM", "27000원", R.drawable.pet9, "1:1 맞춤 케어 · 셀프 목욕실 제공", "서울시 서대문구 연희로 35"),
            Hotel("위글위글 호텔", "★평점:4.7", "거리: 2.7KM", "29000원", R.drawable.pet10, "소형견 전용 힐링 공간 · 조용한 환경", "서울시 동작구 흑석로 99")
        )

        adapter = HotelAdapter(this, hotelList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Spinner 설정
        val spinner = binding.sortSpinner
        ArrayAdapter.createFromResource(
            this,
            R.array.sort_options,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    1 -> hotelList.sortBy { extractKm(it.distance) }            // 거리순
                    2 -> hotelList.sortByDescending { extractRating(it.rating) } // 평점순
                    else -> hotelList.sortBy { it.name }                        // 기본순
                }
                adapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    // "거리: 3.2KM" -> 3.2
    private fun extractKm(text: String): Double {
        return Regex("""(\d+(\.\d+)?)""").find(text)?.value?.toDoubleOrNull() ?: Double.MAX_VALUE
    }

    // "★평점:4.7" -> 4.7
    private fun extractRating(text: String): Double {
        return Regex("""(\d+(\.\d+)?)""").find(text)?.value?.toDoubleOrNull() ?: 0.0
    }
}
