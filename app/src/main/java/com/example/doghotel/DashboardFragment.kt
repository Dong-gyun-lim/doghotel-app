package com.example.doghotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardFragment : Fragment() {

    private lateinit var todayReservationTitle: TextView
    private lateinit var todayReservationRecyclerView: RecyclerView
    private lateinit var todayReservationAdapter: TodayReservationAdapter
    private val todayReservationList = mutableListOf<Reservation>()

    private lateinit var checkinText: TextView
    private lateinit var monthlyRevenueText: TextView
    private lateinit var notificationText: TextView
    private val pendingNotificationList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todayReservationTitle = view.findViewById(R.id.todayReservationTitle)
        todayReservationRecyclerView = view.findViewById(R.id.todayReservationRecyclerView)
        checkinText = view.findViewById(R.id.checkinText)
        monthlyRevenueText = view.findViewById(R.id.monthlyRevenueText)
        notificationText = view.findViewById(R.id.notificationText)

        todayReservationAdapter = TodayReservationAdapter(todayReservationList) { canceledReservation, position ->
            todayReservationAdapter.removeItem(position)
            updateTodayReservationTitle()
        }

        todayReservationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        todayReservationRecyclerView.adapter = todayReservationAdapter

        updateNotificationText()
        updateTodayReservationTitle()

        val checkins = listOf(
            "뽀삐 / 김지민 / 10:00",
            "초코 / 이준호 / 12:30",
            "복돌이 / 최민서 / 15:00"
        )
        checkinText.text = "🐶 오늘 입실 예정: ${checkins.size}마리\n" + checkins.joinToString("\n") { "- $it" }
        monthlyRevenueText.text = "이번 달 매출: 450,000원"
    }

    fun appendReservation(reservation: Reservation) {
        todayReservationList.add(reservation)
        todayReservationAdapter.notifyItemInserted(todayReservationList.size - 1)
        updateTodayReservationTitle()
    }

    fun removeTodayReservation(reservation: Reservation) {
        val index = todayReservationList.indexOfFirst { it.id == reservation.id }
        if (index != -1) {
            todayReservationList.removeAt(index)
            todayReservationAdapter.notifyItemRemoved(index)
            updateTodayReservationTitle()
        }
    }

    fun addNotification(reservation: Reservation) {
        if (!this::notificationText.isInitialized) return
        val entry = "${reservation.guestName} / ${reservation.petName} / ${reservation.checkInDate}"
        pendingNotificationList.add(entry)
        updateNotificationText()
    }

    fun removeNotification(reservation: Reservation) {
        val entry = "${reservation.guestName} / ${reservation.petName} / ${reservation.checkInDate}"
        pendingNotificationList.remove(entry)
        updateNotificationText()
    }

    private fun updateNotificationText() {
        val header = "📢 예약이 새로 들어왔습니다!"
        val body = pendingNotificationList.joinToString("\n")
        notificationText.text = if (body.isEmpty()) header else "$header\n$body"
    }

    private fun updateTodayReservationTitle() {
        todayReservationTitle.text = "📅 오늘 예약: ${todayReservationList.size}건"
    }
}
