package com.example.vamshi.Curious.Screens.PlacedetailsScreen

import com.example.vamshi.Curious.Dagger.Qualifiers.QPlacePhotosGetRequest
import com.example.vamshi.Curious.Model.Retrofit.QueryConstants
import com.example.vamshi.Curious.Model.Retrofit.RemoteRx
import com.example.vamshi.Curious.PlacesEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class PlaceDetailPresenter @Inject constructor(var placesEntity: PlacesEntity,
                                               val PDtailActivity: PlaceDetails,
                                               @QPlacePhotosGetRequest val getplacePhotos: RemoteRx)
    : PlaceDetailContracter {


    override fun getPhotos() {



        var observable = getplacePhotos.QueryPlacePhotos(placesEntity.placeId,
                QueryConstants.FOURSQUARE_CLIENT_KEY,
                QueryConstants.FOURSQUARE_CLIENT_SECRET,
                QueryConstants.YYYMMDD)


        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(PhotoObserver())


    }



}