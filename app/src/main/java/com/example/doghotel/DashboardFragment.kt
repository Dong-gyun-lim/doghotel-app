package com.example.doghotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DashboardFragment : Fragment() {

    private lateinit var todayReservationText: TextView
    private lateinit var checkinText: TextView
    private lateinit var monthlyRevenueText: TextView
    private lateinit var notificationText: TextView

    private var reservationCount = 0
    private val reservationList = mutableListOf<String>()
    private val pendingNotificationList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todayReservationText = view.findViewById(R.id.todayReservationText)
        checkinText = view.findViewById(R.id.checkinText)
        monthlyRevenueText = view.findViewById(R.id.monthlyRevenueText)
        notificationText = view.findViewById(R.id.notificationText)

        updateTodayReservationText()
        updateNotificationText()

        checkinText.text = "오늘 입실 예정: 3마리"
        monthlyRevenueText.text = "이번 달 매출: 450,000원"
    }

    fun appendReservation(reservation: Reservation) {
        val entry = "${reservation.guestName} / ${reservation.petName} / ${reservation.checkInDate}"

        // 오늘 예약 카운트 및 리스트 추가
        reservationCount++
        reservationList.add(entry)
        updateTodayReservationText()

        // ❌ 알림 추가는 여기서 하지 않음
    }

    fun removeNotification(reservation: Reservation) {
        val entry = "${reservation.guestName} / ${reservation.petName} / ${reservation.checkInDate}"

        // 알림에서 제거
        pendingNotificationList.remove(entry)
        updateNotificationText()

        // 오늘 예약 리스트 및 카운트에서도 제거
        if (reservationList.remove(entry)) {
            reservationCount--
            updateTodayReservationText()
        }
    }

    fun addNotification(reservation: Reservation) {
        if (!this::notificationText.isInitialized) return

        val entry = "${reservation.guestName} / ${reservation.petName} / ${reservation.checkInDate}"
        pendingNotificationList.add(entry)
        updateNotificationText()
    }

    private fun updateTodayReservationText() {
        val header = "오늘 예약: $reservationCount"
        val body = reservationList.joinToString("\n")
        todayReservationText.text = if (body.isEmpty()) header else "$header\n$body"
    }

    private fun updateNotificationText() {
        val header = "📢 예약이 새로 들어왔습니다!"
        val body = pendingNotificationList.joinToString("\n")
        notificationText.text = if (body.isEmpty()) header else "$header\n$body"
    }
}
