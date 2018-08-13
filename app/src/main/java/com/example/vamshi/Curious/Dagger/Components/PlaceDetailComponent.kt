package com.example.vamshi.Curious.Dagger.Components

import com.example.vamshi.Curious.Dagger.PlaceDetailModule
import com.example.vamshi.Curious.Dagger.Scopes.PlaceDetailActivityScope
import com.example.vamshi.Curious.PlacesEntity
import com.example.vamshi.Curious.Screens.PlacedetailsScreen.PlaceDetails
import dagger.Component

@Component(modules = arrayOf(PlaceDetailModule::class), dependencies = arrayOf(AppComponent::class))
@PlaceDetailActivityScope
interface PlaceDetailComponent {


    fun Inject(placeDetails: PlaceDetails)

    fun getPlaceDetailsContext(): PlaceDetails

    fun getPlaceEntity(): PlacesEntity


}