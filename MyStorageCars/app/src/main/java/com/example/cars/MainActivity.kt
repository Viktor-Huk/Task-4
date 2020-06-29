package com.example.cars

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cars.adapter.CarAdapter
import com.example.cars.databinding.ActivityMainBinding
import com.example.cars.db.Car
import com.example.cars.db.Manager.DatabaseManager
import com.example.cars.preference.SettingFragment
import com.example.cars.preference.SettingsActivity

class MainActivity : AppCompatActivity() {

    private val carAdapter = CarAdapter()
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

            val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
            val typeSort = sharedPreference.getString(SettingFragment.KEY_PREF_SORT, SettingFragment.TYPE_SORT_NOSORT)

            with(SettingFragment) {

                when(typeSort) {
                    TYPE_SORT_BRAND -> {
                        result?.sortedBy { it.brand }?.let { carAdapter.updateData(it) }
                    }
                    TYPE_SORT_YEAR -> {
                        result?.sortedBy { it.year }?.let { carAdapter.updateData(it) }
                    }
                    TYPE_SORT_PRICE -> {
                        result?.sortedBy { it.price }?.let { carAdapter.updateData(it) }
                    }
                    else -> {
                        result?.let { carAdapter.updateData(it) }
                    }
                }
            }
            super.onPostExecute(result)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        queryTask?.cancel(true)
        queryTask = null
    }
}
