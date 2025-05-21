package com.example.doghotel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewAdapter(private val reviews: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.reviewerName)
        val comment = view.findViewById<TextView>(R.id.reviewComment)
        val rating = view.findViewById<TextView>(R.id.reviewRating)
        val warning = view.findViewById<TextView>(R.id.reviewReported)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.name.text = review.guestName
        holder.comment.text = review.comment
        holder.rating.text = "⭐ ${review.rating}"
        holder.warning.visibility = if (review.isReported) View.VISIBLE else View.GONE
    }

    override fun getItemCount(): Int = reviews.size
}
