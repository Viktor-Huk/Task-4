package com.example.cars.ui

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cars.R
import com.example.cars.ui.adapter.CarAdapter
import com.example.cars.databinding.ActivityMainBinding
import com.example.cars.model.Car
import com.example.cars.db.Manager.DatabaseManager
import com.example.cars.model.SortType
import com.example.cars.preference.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private val carAdapter = CarAdapter(this)
    private lateinit var binding: ActivityMainBinding
    private var queryTask: QueryTask? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainToolbar)

        binding.actionButton.setOnClickListener { addNewCar() }

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = carAdapter
    }

    override fun onStart() {
        super.onStart()
        queryTask = QueryTask()
        queryTask?.execute()
    }

    private fun addNewCar() {
        val addNewCarIntent = Intent(this, AddNewCarActivity::class.java)
        startActivity(addNewCarIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.sort -> {
                val sortIntent = Intent(this, SettingsActivity()::class.java)
                startActivity(sortIntent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private inner class QueryTask : AsyncTask<Void, Void, List<Car>>() {

        override fun doInBackground(vararg params: Void?) = DatabaseManager.getAllCars()

        override fun onPostExecute(result: List<Car>?) {

            val sortType = PreferenceHelper.getSortType()
            val sortResult: List<Car>?

            when (sortType) {

                SortType.BRAND.name -> {
                    sortResult = result?.sortedBy { it.brand }
                }

                SortType.YEAR.name -> {
                    sortResult = result?.sortedBy { it.year }
                }

                SortType.PRICE.name -> {
                    sortResult = result?.sortedBy { it.price }
                }

                else -> {
                    sortResult = result
                }
            }

            sortResult?.let { carAdapter.updateData(it) }

            super.onPostExecute(result)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        queryTask?.cancel(true)
        queryTask = null
    }
}
