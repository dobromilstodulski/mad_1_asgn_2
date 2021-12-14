package org.dobromilstodulski.venues.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class VenueMemStore : VenueStore {

    val venues = ArrayList<VenueModel>()

    override fun findAll(): List<VenueModel> {
        return venues
    }

    override fun create(venue: VenueModel) {
        venue.id = getId()
        venues.add(venue)
        logAll()
    }

    override fun update(venue: VenueModel) {
        val foundVenue: VenueModel? = venues.find { v -> v.id == venue.id }
        if (foundVenue != null) {
            foundVenue.name = venue.name
            foundVenue.description = venue.description
            foundVenue.contact = venue.contact
            foundVenue.type = venue.type
            foundVenue.image = venue.image
            foundVenue.rating = venue.rating
            foundVenue.lat = venue.lat
            foundVenue.lng = venue.lng
            foundVenue.zoom = venue.zoom
            logAll()
        }
    }

    override fun delete(venue: VenueModel) {
        venues.remove(venue)
    }

    private fun logAll() {
        venues.forEach { i("$it") }
    }
}