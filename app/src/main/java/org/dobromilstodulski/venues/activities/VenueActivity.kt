package org.dobromilstodulski.venues.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.databinding.ActivityVenueBinding
import timber.log.Timber
import timber.log.Timber.i

class VenueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVenueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVenueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Venue Activity started...")
    }
}