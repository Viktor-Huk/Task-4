package com.example.cars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cars.db.Car
import com.example.cars.R

class CarAdapter : RecyclerView.Adapter<CarViewHolder>() {

    private var dataSet: List<Car> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_car, parent, false)

        return CarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.brandTextView.text = "BRAND: ${dataSet[position].brand}"
        holder.yearTextView.text = "YEAR: ${dataSet[position].year}"
        holder.priceTextView.text = "PRICE: ${dataSet[position].price}"
    }

    fun updateData(newList: List<Car>) {
        this.dataSet = newList
        notifyDataSetChanged()
    }
}
