package com.example.doghotel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DogUserAdapter(private val users: List<DogUser>) :
    RecyclerView.Adapter<DogUserAdapter.ViewHolder>() {

    var selectedPosition = -1  // ✅ 기본값: 아무것도 선택되지 않음

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.userCheckBox)
        val ownerNameText: TextView = view.findViewById(R.id.ownerNameTextView)
        val dogNameText: TextView = view.findViewById(R.id.dogNameTextView)
        val dogAgeText: TextView = view.findViewById(R.id.dogAgeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dog_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.ownerNameText.text = "이름: ${user.ownerName}"
        holder.dogNameText.text = "반려견: ${user.dogName}"
        holder.dogAgeText.text = "반려견 나이: ${user.dogAge}살"

        holder.checkBox.isChecked = position == selectedPosition

        holder.checkBox.setOnClickListener {
            val realPosition = holder.adapterPosition
            if (realPosition != RecyclerView.NO_POSITION && selectedPosition != realPosition) {
                selectedPosition = realPosition
                notifyDataSetChanged()
            }
        }
    }

    // ✅ 선택된 사용자 반환 함수
    fun getSelectedUser(): DogUser? {
        return if (selectedPosition in users.indices) users[selectedPosition] else null
    }
}
