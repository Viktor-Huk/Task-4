package com.example.cars.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.cars.db.Entity.CarEntity
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

class DatabaseHelper(context: Context) : OrmLiteSqliteOpenHelper(context, "cars.db", null, 1) {

    companion object {

        lateinit var dbHelperContext: DatabaseHelper
    }

    init {
        dbHelperContext = this
    }

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        TableUtils.createTableIfNotExists(connectionSource, CarEntity::class.java)
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int
    ) {
        //TODO
    }
}