package com.example.cars.db.Entity

import com.example.cars.db.Car
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "cars")
data class CarEntity(

    @DatabaseField(generatedId = true)
    var id: Int? = null,

    @DatabaseField
    var brand: String? = "",

    @DatabaseField
    private var year: Int? = null,

    @DatabaseField
    private var price: Int? = null
) {
    fun toCar() : Car {
        return Car(brand, year, price)
    }
}