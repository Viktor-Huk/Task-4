package com.example.cars.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cars.model.Car
import com.example.cars.R

class CarAdapter(private val context: Context) : RecyclerView.Adapter<CarViewHolder>() {

    private var dataSet: List<Car> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_car, parent, false)

        return CarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {

        with(holder) {
            brandTextView.text = brandTextView.context.getString(R.string.item_template_brand, dataSet[position].brand)
            yearTextView.text = brandTextView.context.getString(R.string.item_template_year, dataSet[position].year)
            priceTextView.text = brandTextView.context.getString(R.string.item_template_price, dataSet[position].price)
        }
    }

    fun updateData(newList: List<Car>) {
        this.dataSet = newList
        notifyDataSetChanged()
    }
}
