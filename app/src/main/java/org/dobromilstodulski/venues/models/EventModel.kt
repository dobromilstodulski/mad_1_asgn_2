package org.dobromilstodulski.venues.models

class EventModel(
    var title: String = "",
    var description: String="",
    var ticket: String="",
    var type: String="",
    var time: String="",
    var date: Long,
    var organiser: String="",)