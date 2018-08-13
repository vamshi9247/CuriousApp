package com.example.vamshi.Curious.Dagger

import com.example.vamshi.Curious.Dagger.Scopes.PlaceDetailActivityScope
import com.example.vamshi.Curious.PlacesEntity
import com.example.vamshi.Curious.Screens.PlacedetailsScreen.PlaceDetails
import dagger.Module
import dagger.Provides


@Module
class PlaceDetailModule(val placesEntity: PlacesEntity,
                        val placeDetails: PlaceDetails) {


    @Provides
    @PlaceDetailActivityScope
    fun getPhotoEntity():PlacesEntity{

        return placesEntity
    }

    @Provides
    @PlaceDetailActivityScope
    fun getPlacedetails():PlaceDetails{

        return placeDetails
    }


}