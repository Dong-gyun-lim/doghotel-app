package com.example.doghotel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelAdapter(
    private val context: Context,
    private val hotelList: List<Hotel>
) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val ratingTextView: TextView = itemView.findViewById(R.id.ratingTextView)
        val distanceTextView: TextView = itemView.findViewById(R.id.distanceTextView)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hotelList[position]
        holder.imageView.setImageResource(hotel.imageResId)
        holder.nameTextView.text = hotel.name
        holder.ratingTextView.text = hotel.rating
        holder.distanceTextView.text = hotel.distance
        holder.priceTextView.text = hotel.price

        holder.itemView.setOnClickListener {
            val intent = Intent(context, HotelDetailActivity::class.java).apply {
                putExtra("imageResId", hotel.imageResId)
                putExtra("name", hotel.name)
                putExtra("rating", hotel.rating)
                putExtra("distance", hotel.distance)
                putExtra("price", hotel.price)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = hotelList.size
}
