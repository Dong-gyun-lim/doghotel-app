package com.example.doghotel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

class ProviderMainActivity : AppCompatActivity() {

    private var dashboardFragment: DashboardFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_main)

        // ✅ ArrayList로 정의
        val dummyList = arrayListOf(
            Reservation("1", "김철수", "멍멍이", "2025-05-18", "예약완료", "예민함 주의"),
            Reservation("2", "이영희", "복슬이", "2025-05-19", "예약완료", "크게 짖음"),
            Reservation("3", "박민수", "바둑이", "2025-05-20", "예약완료", "약 알러지 있음")

        )

        // Fragment 인스턴스 생성
        dashboardFragment = DashboardFragment()
        val reservationFragment = ReservationFragment.newInstance(dummyList)
        val reviewFragment = ReviewFragment()

        // Fragment 연결
        supportFragmentManager.commit {
            replace(R.id.reservationFragmentContainer, reservationFragment)
            replace(R.id.reviewFragmentContainer, reviewFragment)
            replace(R.id.dashboardFragmentContainer, dashboardFragment!!)
        }
    }

    // ✅ 초기 알림 등록 (ReservationFragment에서 호출)
    fun handleInitialNotification(reservation: Reservation) {
        dashboardFragment?.addNotification(reservation)
    }

    // ✅ 승인 시: 알림 제거 + 오늘 예약에 추가
    fun handleApprovedReservation(reservation: Reservation) {
        dashboardFragment?.removeNotification(reservation)
        dashboardFragment?.appendReservation(reservation)
    }

    // ✅ 거절 시: 알림만 제거
    fun handleDeclinedReservation(reservation: Reservation, reason: String) {
        dashboardFragment?.removeNotification(reservation)
        // 사유 로그 남기거나 Toast로 표시도 가능
        Log.d("거절사유", "→ ${reservation.guestName}: $reason")
    }
}
