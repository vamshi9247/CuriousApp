package com.example.vamshi.Curious.Screens.MainScreen

interface ContractPresenterView {

    interface PresenterMainWork {

        fun getPlaces(s: String): Boolean

        fun addFavourite(key: String)

        fun removeFavourite(key: String)

        fun isFavourite(key: String): Boolean

        fun maStart()

        fun maStop()
    }
}
