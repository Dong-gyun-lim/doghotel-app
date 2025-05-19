package com.example.doghotel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class ProviderMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_main)

        // 예약 관리 Fragment 추가
        supportFragmentManager.commit {
            replace(R.id.reservationFragmentContainer, ReservationFragment())
        }

        // 리뷰 관리 Fragment 추가
        supportFragmentManager.commit {
            replace(R.id.reviewFragmentContainer, ReviewFragment())
        }
        supportFragmentManager.commit {
            replace(R.id.dashboardFragmentContainer, DashboardFragment())
        }
    }
}