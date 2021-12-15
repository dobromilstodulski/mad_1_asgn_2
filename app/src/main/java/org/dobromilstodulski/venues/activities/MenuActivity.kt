package org.dobromilstodulski.venues.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.databinding.ActivityMenuBinding
import org.dobromilstodulski.venues.main.MainApp

class MenuActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        binding.btnEvents.setOnClickListener{
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }

        binding.btnVenues.setOnClickListener{
            val intent = Intent(this, VenueListActivity::class.java)
            startActivity(intent)
        }

        binding.btnPlaces.setOnClickListener{
            val intent = Intent(this, PlacesActivity::class.java)
            startActivity(intent)
        }

        binding.btnEB.setOnClickListener{
            val intent = Intent(this, EventEventbriteActivity::class.java)
            startActivity(intent)
        }

        binding.btnSettings.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}