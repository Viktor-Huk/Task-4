package com.example.cars.preference

import android.os.Bundle
import android.view.View
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.cars.R

class SettingFragment : PreferenceFragmentCompat() {

    private var select: ListPreference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        select = findPreference(PreferenceHelper.TYPE_SORT_NOSORT)
    }

    companion object {
        const val KEY_PREF_SORT = "sort"
        const val TYPE_SORT_BRAND = "brand"
        const val TYPE_SORT_YEAR = "year"
        const val TYPE_SORT_PRICE = "price"
        const val TYPE_SORT_NOSORT = "nosort"
    }
}