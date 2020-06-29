package com.example.cars.preference

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cars.R


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settingsFragmentContent, SettingFragment())
            .commit()
        setSupportActionBar(findViewById(R.id.settingsTolbar))
    }
}