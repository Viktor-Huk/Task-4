package com.example.cars.preference

import android.content.Context
import com.example.cars.App
import com.example.cars.model.SortType

object PreferenceHelper {

    private val prefs by lazy {
        App.INSTANCE.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
    }

    private const val SORT_TYPE = "sort"
    private const val PREFS = "CarPrefs"

    fun getSortType(): String = prefs.getString(SORT_TYPE, "")?:""

    fun setSortType(sortType: SortType) {
        prefs.edit().putString(SORT_TYPE, sortType.name).apply()
    }
}