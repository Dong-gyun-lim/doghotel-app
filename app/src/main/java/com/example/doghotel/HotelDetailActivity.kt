package com.example.doghotel

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton

class HotelDetailActivity : AppCompatActivity() {

    private lateinit var weightInput: EditText
    private lateinit var weekendCheckBox: CheckBox
    private lateinit var neuteredCheckBox: CheckBox
    private lateinit var finalPriceTextView: TextView
    private var hotelName: String = ""
    private var basePrice: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detail)

        try {
            // 툴바
            val toolbar = findViewById<Toolbar>(R.id.detailToolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            // 호텔 데이터 수신
            val imageResId = intent.getIntExtra("imageResId", 0)
            hotelName = intent.getStringExtra("name") ?: "이름 없음"
            val rating = intent.getStringExtra("rating") ?: "평점 정보 없음"
            val distance = intent.getStringExtra("distance") ?: "거리 정보 없음"
            basePrice = intent.getStringExtra("price") ?: "가격 정보 없음"
            val description = intent.getStringExtra("description") ?: "소개글 없음"
            val address = intent.getStringExtra("address") ?: "주소 없음"

            // 바인딩
            findViewById<ImageView>(R.id.detailImageView).setImageResource(imageResId)
            findViewById<TextView>(R.id.detailNameTextView).text = hotelName
            findViewById<TextView>(R.id.detailRatingTextView).text = "$rating · $distance"
            findViewById<TextView>(R.id.detailPriceTextView).text = "기본가격: $basePrice"
            findViewById<TextView>(R.id.detailDescriptionTextView).text = description
            findViewById<TextView>(R.id.detailAddressTextView).text = address

            // 실시간 가격 계산 요소
            weightInput = findViewById(R.id.weightInput)
            weekendCheckBox = findViewById(R.id.weekendCheckBox)
            neuteredCheckBox = findViewById(R.id.neuteredCheckBox)
            finalPriceTextView = findViewById(R.id.finalPriceTextView)

            val onChangeListener = {
                updatePrice()
            }

            weightInput.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) = updatePrice()
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })

            weekendCheckBox.setOnCheckedChangeListener { _, _ -> onChangeListener() }
            neuteredCheckBox.setOnCheckedChangeListener { _, _ -> onChangeListener() }

            // 예약 버튼
            findViewById<MaterialButton>(R.id.reserveButton).setOnClickListener {
                val finalPriceText = finalPriceTextView.text.toString()
                AlertDialog.Builder(this)
                    .setTitle("예약 확인")
                    .setMessage(
                        "정말로 예약하시겠습니까?\n\n" +
                                "💰 $finalPriceText\n" +
                                "⚠️ 추가요금이 발생할 수 있습니다!"
                    )
                    .setPositiveButton("예") { _, _ ->
                        Toast.makeText(this, "예약이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .setNegativeButton("아니오", null)
                    .show()
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "화면 로딩 실패: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }


    private fun updatePrice() {
        val weightText = weightInput.text.toString()
        val dogWeight = weightText.toDoubleOrNull() ?: return
        val isWeekend = weekendCheckBox.isChecked
        val isNeutered = neuteredCheckBox.isChecked

        val price = PriceCalculator.computePrice(hotelName, dogWeight, isWeekend, isNeutered)
        finalPriceTextView.text = "실제 결제 금액: ${price}원"
        finalPriceTextView.setTextColor(getColor(android.R.color.holo_red_dark))
    }

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
