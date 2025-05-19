package com.example.doghotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReservationFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ReservationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_reservation, container, false)
        recyclerView = view.findViewById(R.id.reservationRecyclerView)

        val dummyReservations = listOf(
            Reservation("1", "김철수", "멍멍이", "2025-05-18", "예약완료", "예민함 주의"),
            Reservation("2", "이영희", "복슬이", "2025-05-19", "예약완료", "크게 짖음"),
            Reservation("3", "박민수", "바둑이", "2025-05-20", "예약완료", "약 알러지 있음")
        )

        adapter = ReservationAdapter(dummyReservations)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        return view
    }
}
