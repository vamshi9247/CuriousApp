package com.example.vamshi.Curious.Model


import java.util.ArrayList

internal data class JsonOutput(var meta: Meta,
                               var response: Response)

internal data class Meta(var code: Int,
                         var requestId: String)

internal data class Response(var venues: List<Venues>)

internal data class Venues(
        var id: String,
        var name: String,
        var location: Location,
        var categories: List<Categories>,
        var venuePage: VenuePage)

internal data class Location(var address: String,
                             var crossStreet: String,
                             var lat: Double,
                             var lng: Double,
                             var labeledLatLngs: List<LabeledLatLngs>,
                             var distance: Int,
                             var postalCode: String,
                             var cc: String,
                             var city: String,
                             var state: String,
                             var country: String,
                             var formattedAddress: ArrayList<String>)

internal data class VenuePage(var id: String)

internal data class LabeledLatLngs(var label: String,
                                   var lat: Double,
                                   var lng: Double)

internal data class Categories(var id: String,
                               var name: String,
                               var pluralName: String,
                               var shortName: String,
                               var icon: Icon,
                               var primary: Boolean)

internal data class Icon(var prefix: String,
                         var suffix: String)



internal data class Root(var meta: Meta,
                         var response: Response)
