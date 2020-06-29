package com.example.cars

import android.app.Application
import com.example.cars.db.DatabaseHelper

class App : Application() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate() {
        super.onCreate()
        dbHelper = DatabaseHelper(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        dbHelper.close()
    }
}