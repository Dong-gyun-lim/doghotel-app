package com.example.doghotel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.doghotel.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ 회원 버튼 → MapActivity로 이동 (수정됨)
        binding.memberButton.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }

        // 사장님 버튼 → ProviderMainActivity로 이동
        binding.ownerButton.setOnClickListener {
            startActivity(Intent(this, ProviderMainActivity::class.java))
        }
    }
}
