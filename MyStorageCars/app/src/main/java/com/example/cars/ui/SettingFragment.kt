package com.example.cars.ui

import android.os.Bundle
import android.view.View
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.cars.R
import com.example.cars.model.SortType
import com.example.cars.preference.PreferenceHelper

class SettingFragment : PreferenceFragmentCompat() {

    private var select: ListPreference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        select = findPreference(SortType.NO_SORT.name)
    }
}