package org.dobromilstodulski.venues.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import timber.log.Timber
import com.google.gson.reflect.TypeToken
import org.dobromilstodulski.venues.helpers.exists
import org.dobromilstodulski.venues.helpers.read
import org.dobromilstodulski.venues.helpers.write
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

const val JSON_FILE = "venues.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<VenueModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class VenueJSONStore(private val context: Context) : VenueStore {

    var venues = mutableListOf<VenueModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<VenueModel> {
        logAll()
        return venues
    }

    override fun create(venue: VenueModel) {
        venue.id = generateRandomId()
        venues.add(venue)
        serialize()
    }


    override fun update(venue: VenueModel) {
        val venuesList = findAll() as ArrayList<VenueModel>
        var foundVenue: VenueModel? = venuesList.find { v -> v.id == venue.id }
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
        }
        serialize()
    }

    override fun delete(venue: VenueModel) {
        venues.remove(venue)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(venues, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        venues = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        venues.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>, JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}