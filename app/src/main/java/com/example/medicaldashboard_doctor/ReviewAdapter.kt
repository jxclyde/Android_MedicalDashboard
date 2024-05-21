package com.example.medicaldashboard_doctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewsAdapter(private val reviewsList: MutableList<Review>) : RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {

    fun updateReviews(newReviews: List<Review>) {
        reviewsList.clear()
        reviewsList.addAll(newReviews)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ReviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val currentItem = reviewsList[position]
        holder.userNameTextView.text = "${currentItem.nickName}"
        holder.reviewTextView.text = currentItem.reviewsText



//        holder.userImageView.setImageResource(currentItem.userImage)
//        holder.userNameTextView.text = currentItem.userName
//        holder.reviewTextView.text = currentItem.reviewText
    }

    override fun getItemCount() = reviewsList.size

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImageView: ImageView = itemView.findViewById(R.id.userImageView)
        val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
        val reviewTextView: TextView = itemView.findViewById(R.id.reviewTextView)
    }
}
