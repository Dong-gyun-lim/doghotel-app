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

        // 회원 버튼 → 기존 MainActivity 이동
        binding.memberButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // 사장님 버튼 → 미구현 (Toast로 안내)
        binding.ownerButton.setOnClickListener {
            // 추후 연결 예정
            // startActivity(Intent(this, OwnerActivity::class.java))
        }
    }
}
