package org.dobromilstodulski.venues.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.databinding.ActivityMenuBinding
import org.dobromilstodulski.venues.databinding.ActivitySettingsBinding
import org.dobromilstodulski.venues.main.MainApp

class SettingsActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

       binding.switch1.setOnCheckedChangeListener { _, isChecked ->

           // if the button is checked, i.e., towards the right or enabled
           // enable dark mode, change the text to disable dark mode
           // else keep the switch text to enable dark mode
           if (isChecked) {
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
               binding.switch1.text = "Disable dark mode"
           } else {
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
               binding.switch1.text = "Enable dark mode"
           }}
    }
}