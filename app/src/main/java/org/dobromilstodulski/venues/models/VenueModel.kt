package org.dobromilstodulski.venues.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VenueModel (var id: Long = 0,
                       var name: String = "",
                       var description: String="",
                       var contact: String="",
                       var type: String="",
                       var image: Uri = Uri.EMPTY,
                       var rating: Double = 0.0,
                       var lat : Double = 0.0,
                       var lng: Double = 0.0,
                       var zoom: Float = 0f) : Parcelable

@Parcelize
data class Location (var lat: Double = 0.0,
                     var lng: Double = 0.0,
                     var zoom: Float = 0f) : Parcelable