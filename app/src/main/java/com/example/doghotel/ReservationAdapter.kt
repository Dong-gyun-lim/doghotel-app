package com.example.doghotel

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class ReservationAdapter(
    private val reservations: MutableList<Reservation>,
    private val onApproved: (Reservation) -> Unit,
    private val onDeclined: (Reservation, String) -> Unit // 거절 사유 추가
) : RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {

    class ReservationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val guestNameText: TextView = view.findViewById(R.id.guestNameText)
        val petNameText: TextView = view.findViewById(R.id.petNameText)
        val statusText: TextView = view.findViewById(R.id.statusText)
        val dateText: TextView = view.findViewById(R.id.reservationDateText)
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
        holder.dateText.text = "날짜: ${item.checkInDate}"

        holder.approveBtn.setOnClickListener {
            Toast.makeText(it.context, "${item.guestName} 예약 승인됨", Toast.LENGTH_SHORT).show()
            onApproved(item)
            reservations.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, reservations.size)
        }

        holder.declineBtn.setOnClickListener {
            showDeclineReasonDialog(it.context, item, position)
        }
    }

    private fun showDeclineReasonDialog(context: Context, reservation: Reservation, position: Int) {
        val editText = EditText(context).apply {
            hint = "거절 사유를 입력하세요"
        }

        AlertDialog.Builder(context)
            .setTitle("예약 거절")
            .setMessage("${reservation.guestName} 예약을 거절하시겠습니까?")
            .setView(editText)
            .setPositiveButton("확인") { _, _ ->
                val reason = editText.text.toString()
                Toast.makeText(context, "${reservation.guestName} 예약 거절됨\n사유: $reason", Toast.LENGTH_SHORT).show()
                onDeclined(reservation, reason)
                reservations.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, reservations.size)
            }
            .setNegativeButton("취소", null)
            .show()
    }

    override fun getItemCount(): Int = reservations.size
}
