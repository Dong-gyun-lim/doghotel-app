package com.example.doghotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val todayReservationText = view.findViewById<TextView>(R.id.todayReservationText)
        val checkinText = view.findViewById<TextView>(R.id.checkinText)
        val monthlyRevenueText = view.findViewById<TextView>(R.id.monthlyRevenueText)
        val notificationText = view.findViewById<TextView>(R.id.notificationText)

        // TODO: 나중에 DB 연동 시 이 값들은 실시간으로 갱신할 수 있음
        todayReservationText.text = "오늘 예약: 5건"
        checkinText.text = "오늘 입실 예정: 3마리"
        monthlyRevenueText.text = "이번 달 매출: 450,000원"
        notificationText.text = "📢 예약이 새로 들어왔습니다!"

        return view
    }
}
