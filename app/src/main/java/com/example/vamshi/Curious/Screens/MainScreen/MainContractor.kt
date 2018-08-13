package com.example.vamshi.Curious.Screens.MainScreen

import android.location.Location

interface MainContractor {

    interface PresenterMainWork {

        fun getPlaces(s: String): Boolean

        fun currentLocation(location: Location?)

        fun addFavourite(key: String)

        fun removeFavourite(key: String)

        fun isFavourite(key: String): Boolean

        fun maStart()

        fun maStop()
    }
}
