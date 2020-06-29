package com.example.cars.db.Manager

import com.example.cars.db.Car
import com.example.cars.db.DatabaseHelper
import com.example.cars.db.Entity.CarEntity

object DatabaseManager {

    private val carDao = DatabaseHelper.dbHelperContext.getDao(CarEntity::class.java)

    fun add(newCar: Array<out Car?>?) {
        carDao.createOrUpdate(
            CarEntity(
                null,
                newCar?.get(0)?.brand,
                newCar?.get(0)?.year,
                newCar?.get(0)?.price
            )
        )
    }

    fun getAllCars() = carDao.queryForAll().map { it.toCar() }
}

