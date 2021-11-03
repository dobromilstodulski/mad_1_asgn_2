package org.dobromilstodulski.venues.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.dobromilstodulski.venues.R
import org.dobromilstodulski.venues.databinding.ActivityEventBinding
import timber.log.Timber
import timber.log.Timber.i

class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Event Activity started...")
    }
}