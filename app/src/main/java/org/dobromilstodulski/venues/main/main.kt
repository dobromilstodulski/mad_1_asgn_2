package org.dobromilstodulski.venues.main

import android.app.Application
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    //val events = EventMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Placemark started")
    }
}