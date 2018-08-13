package com.example.vamshi.Curious

import com.example.vamshi.Curious.Model.Retrofit.QueryConstants

data class PhotoEntity(var prefix: String = "",
                       var photo_size: String = "300x300",
                       var suffix: String = "") {
    var placeid:String? = null

    constructor(placesEntity: PlacesEntity): this(photo_size= "300x300"){
        this.placeid =
                "https://maps.googleapis.com/maps/api/staticmap?center=${placesEntity.lat},${placesEntity.lng}&" +
                "zoom=14&" +
                "format=jpg&" +
                "maptype=satellite&"+
                "size=${photo_size}&" +
                "markers=color:red%7Clabel:${placesEntity.name.get(0)}%7C${placesEntity.lat},${placesEntity.lng}&" +
                "scale=2&" +
                "key= ${QueryConstants.GMAPSSTATICKEY}"

    }



    fun toUrl(): String? {

        return if(prefix == "") placeid else "${prefix + photo_size + suffix}"

    }





}