package com.example.doghotel

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton

class HotelDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detail)

        // 툴바 설정
        val toolbar = findViewById<Toolbar>(R.id.detailToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true) // ← 아이콘 활성화
        }

        // 인텐트 데이터 받아오기
        val imageResId = intent.getIntExtra("imageResId", 0)
        val name = intent.getStringExtra("name") ?: "이름 없음"
        val rating = intent.getStringExtra("rating") ?: "평점 정보 없음"
        val distance = intent.getStringExtra("distance") ?: "거리 정보 없음"
        val price = intent.getStringExtra("price") ?: "가격 정보 없음"
        val description = intent.getStringExtra("description") ?: "설명 정보 없음"
        val address = intent.getStringExtra("address") ?: "주소 정보 없음"

        // 데이터 바인딩
        findViewById<ImageView>(R.id.detailImageView).setImageResource(imageResId)
        findViewById<TextView>(R.id.detailNameTextView).text = name
        findViewById<TextView>(R.id.detailRatingTextView).text = "$rating · $distance"
        findViewById<TextView>(R.id.detailPriceTextView).text = price
        findViewById<TextView>(R.id.detailDescriptionTextView).text = description
        findViewById<TextView>(R.id.detailAddressTextView).text = address

        // 예약 버튼 클릭 이벤트
        findViewById<MaterialButton>(R.id.reserveButton).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("예약 확인")
                .setMessage("정말로 예약하시겠습니까?")
                .setPositiveButton("예") { _, _ ->
                    Toast.makeText(this, "예약이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("아니오", null)
                .show()
        }
    }

    // ← 버튼 눌렀을 때 뒤로 가기
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
