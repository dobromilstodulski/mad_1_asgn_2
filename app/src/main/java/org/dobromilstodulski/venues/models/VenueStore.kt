package org.dobromilstodulski.venues.models

interface VenueStore {
    fun findAll(): List<VenueModel>
    fun create(venue: VenueModel)
    fun update(venue: VenueModel)
    fun delete(venue: VenueModel)
}