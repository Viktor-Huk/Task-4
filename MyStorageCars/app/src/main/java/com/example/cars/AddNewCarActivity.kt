package com.example.cars

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cars.databinding.ActivityAddNewCarBinding
import com.example.cars.db.Car
import com.example.cars.db.Manager.DatabaseManager

class AddNewCarActivity : AppCompatActivity() {

    private val dbManager = DatabaseManager
    private lateinit var binding: ActivityAddNewCarBinding
    private var addCarTask: MyTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { addNewCar() }

        setSupportActionBar(findViewById(R.id.addCarToolbar))
    }

    private fun addNewCar() {
        val newCar = Car(
            binding.brand.text.toString(),
            binding.year.text.toString().toInt(),
            binding.price.text.toString().toInt()
        )

        addCarTask = MyTask()
        addCarTask?.execute(newCar)
    }

    inner class MyTask : AsyncTask<Car, Void, Void>() {

        override fun doInBackground(vararg params: Car?): Void? {
            dbManager.add(params)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            finish()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        addCarTask?.cancel(true)
        addCarTask = null
    }
}