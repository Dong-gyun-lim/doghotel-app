package com.example.doghotel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ReservationAdapter(private val reservations: List<Reservation>) :
    RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {

    class ReservationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val guestNameText: TextView = view.findViewById(R.id.guestNameText)
        val petNameText: TextView = view.findViewById(R.id.petNameText)
        val statusText: TextView = view.findViewById(R.id.statusText)
        val approveBtn: Button = view.findViewById(R.id.approveButton)
        val declineBtn: Button = view.findViewById(R.id.declineButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reservation, parent, false)
        return ReservationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val item = reservations[position]
        holder.guestNameText.text = "${item.guestName} (예약자)"
        holder.petNameText.text = "반려견: ${item.petName}"
        holder.statusText.text = "상태: ${item.status}"

        holder.approveBtn.setOnClickListener {
            Toast.makeText(it.context, "${item.guestName} 예약 승인됨", Toast.LENGTH_SHORT).show()
        }

        holder.declineBtn.setOnClickListener {
            Toast.makeText(it.context, "${item.guestName} 예약 거절됨", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = reservations.size
}
