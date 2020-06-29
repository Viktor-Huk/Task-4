package com.example.cars.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cars.R

class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val brandTextView = itemView.findViewById<TextView>(R.id.brandTextView)
    val yearTextView = itemView.findViewById<TextView>(R.id.yearTextView)
    val priceTextView = itemView.findViewById<TextView>(R.id.priceTextView)
}