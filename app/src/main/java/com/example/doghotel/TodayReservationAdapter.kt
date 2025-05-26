package com.example.doghotel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodayReservationAdapter(
    private val reservations: MutableList<Reservation>,
    private val onCancel: (Reservation, Int) -> Unit
) : RecyclerView.Adapter<TodayReservationAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val reservationInfoText: TextView = view.findViewById(R.id.todayReservationText)
        val cancelButton: Button = view.findViewById(R.id.cancelTodayReservationBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_today_reservation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reservation = reservations[position]
        holder.reservationInfoText.text =
            "${reservation.guestName} / ${reservation.petName} / ${reservation.checkInDate}"

        holder.cancelButton.setOnClickListener {
            onCancel(reservation, holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = reservations.size

    fun removeItem(position: Int) {
        reservations.removeAt(position)
        notifyItemRemoved(position)
    }
}
