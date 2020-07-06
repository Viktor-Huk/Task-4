package com.example.cars.db.Manager

import com.example.cars.model.Car
import com.example.cars.db.DatabaseHelper
import com.example.cars.db.Entity.CarEntity

object DatabaseManager {

    private val carDao = DatabaseHelper.dbHelperContext.getDao(CarEntity::class.java)

    fun add(newCar: Car) {
        carDao.createOrUpdate(
            CarEntity(
                null,
                newCar.brand,
                newCar.year,
                newCar.price
            )
        )
    }

    fun getAllCars() = carDao.queryForAll().map { it.toCar() }
}

