package com.example.doghotel

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MapActivity : AppCompatActivity() {

    private lateinit var bottomCard: View
    private lateinit var btnList: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        bottomCard = findViewById(R.id.bottomCardInLayout)
        btnList = findViewById(R.id.btnList)

        // ✅ 목록 버튼 클릭 시 MainActivity로 이동
        btnList.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // ✅ 카드 닫기 버튼 클릭 시 카드 숨기기 및 버튼 위치 원래대로
        val closeBtn = bottomCard.findViewById<ImageView>(R.id.btnCloseCard)
        closeBtn.setOnClickListener {
            bottomCard.visibility = View.GONE
            btnList.animate()
                .translationY(0f)
                .setDuration(300)
                .start()
        }

        // ✅ 마커 클릭 이벤트 등록
        findViewById<ImageView>(R.id.marker1).setOnClickListener {
            showBottomCard("청구시장호텔", "대구 동구 청구로", "30,000원", R.drawable.pet1)
        }

        findViewById<ImageView>(R.id.marker2).setOnClickListener {
            showBottomCard("신천1동호텔", "대구 동구 신천동", "28,000원", R.drawable.pet2)
        }

        findViewById<ImageView>(R.id.marker3).setOnClickListener {
            showBottomCard("경북대병원호텔", "대구 중구 삼덕동", "32,000원", R.drawable.pet3)
        }

        findViewById<ImageView>(R.id.marker4).setOnClickListener {
            showBottomCard("동대구역호텔", "대구 동구 신암동", "34,000원", R.drawable.pet4)
        }

        findViewById<ImageView>(R.id.marker5).setOnClickListener {
            showBottomCard("이마트호텔", "대구 동구 효목동", "29,000원", R.drawable.pet5)
        }

        findViewById<ImageView>(R.id.marker6).setOnClickListener {
            showBottomCard("신세계백화점호텔", "대구 동구 동부로", "33,000원", R.drawable.pet6)
        }

        findViewById<ImageView>(R.id.marker7).setOnClickListener {
            showBottomCard("경대병원역호텔", "대구 중구 달성로", "27,000원", R.drawable.pet7)
        }

        findViewById<ImageView>(R.id.marker8).setOnClickListener {
            showBottomCard("범어호텔", "대구 수성구 범어동", "31,000원", R.drawable.pet8)
        }
    }

    // ⬇️ 카드에 데이터 세팅하고 표시 + 목록 버튼 위치 조정
    private fun showBottomCard(name: String, location: String, price: String, imageResId: Int) {
        val hotelName = bottomCard.findViewById<TextView>(R.id.tvHotelName)
        val hotelLocation = bottomCard.findViewById<TextView>(R.id.tvHotelLocation)
        val hotelPrice = bottomCard.findViewById<TextView>(R.id.tvHotelPrice)
        val hotelImage = bottomCard.findViewById<ImageView>(R.id.ivHotelImage)

        hotelName.text = name
        hotelLocation.text = location
        hotelPrice.text = price
        hotelImage.setImageResource(imageResId)

        bottomCard.visibility = View.VISIBLE

        // 💡 layout 완료 후 높이 계산해서 버튼 위로 올리기
        bottomCard.post {
            btnList.animate()
                .translationY(-bottomCard.height.toFloat())
                .setDuration(300)
                .start()
        }
    }
}
