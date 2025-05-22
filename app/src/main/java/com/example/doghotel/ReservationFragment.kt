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
    private val reservationList = mutableListOf<Reservation>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bundle로 전달받은 예약 목록 복원
        arguments?.getParcelableArrayList<Reservation>("reservation_list")?.let {
            reservationList.addAll(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_reservation, container, false)
        recyclerView = view.findViewById(R.id.reservationRecyclerView)

        adapter = ReservationAdapter(
            reservationList,
            onApproved = { approved ->
                (activity as? ProviderMainActivity)?.handleApprovedReservation(approved)
            },
            onDeclined = { declined, reason ->
                (activity as? ProviderMainActivity)?.handleDeclinedReservation(declined, reason)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        // 초기 알림 등록
        reservationList.forEach {
            (activity as? ProviderMainActivity)?.handleInitialNotification(it)
        }
    }

    companion object {
        fun newInstance(reservations: List<Reservation>): ReservationFragment {
            val fragment = ReservationFragment()
            val bundle = Bundle().apply {
                putParcelableArrayList("reservation_list", ArrayList(reservations))
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}
