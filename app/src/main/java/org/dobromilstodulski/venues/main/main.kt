package org.dobromilstodulski.venues.main

import android.app.Application
import org.dobromilstodulski.venues.models.VenueJSONStore
import org.dobromilstodulski.venues.models.VenueStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var venues: VenueStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        venues = VenueJSONStore(applicationContext)
        i("Venues started")
    }
}