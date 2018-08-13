package com.example.vamshi.Curious.Screens.MainScreen

import android.content.SharedPreferences
import android.location.Location

import com.example.vamshi.Curious.Dagger.Qualifiers.QFourSquareGetRequest
import com.example.vamshi.Curious.Model.JsonOutput
import com.example.vamshi.Curious.Model.Retrofit.QueryConstants
import com.example.vamshi.Curious.Model.Retrofit.RemoteRx
import com.google.android.gms.maps.model.LatLng

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import kotlin.math.truncate

class MainPresenter @Inject
constructor(@param:QFourSquareGetRequest internal var callClient: RemoteRx,
            internal var favourites: SharedPreferences?) : MainContractor.PresenterMainWork {
    internal var rxdisposable: Disposable? = null
    private var mlocation: String = "47.6,-122.3"

    override fun getPlaces(query: String): Boolean {

        val observable = callClient.QueryPlaceDetails(
                mlocation,
                QueryConstants.INTENT,
                QueryConstants.limit,
                QueryConstants.RADIUS,
                query,
                QueryConstants.FOURSQUARE_CLIENT_KEY,
                QueryConstants.FOURSQUARE_CLIENT_SECRET,
                QueryConstants.YYYMMDD)


        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(CallObserver())

        return true
    }


    override fun currentLocation(location: Location?) {
        var lat:Double = truncate(location?.latitude ?: 38.89)
        var lng:Double = truncate(location?.longitude ?: 77.03)

        QueryConstants.CURRENT_PLACE = LatLng(lat,lng)
        mlocation = "${lat},${lng}"
    }

    override fun addFavourite(key: String) {

        val spEdit = favourites!!.edit()
        spEdit.putBoolean(key, true)
        spEdit.commit()

    }

    override fun removeFavourite(key: String) {
        val spEdit = favourites!!.edit()
        spEdit.remove(key)
        spEdit.commit()
    }

    override fun isFavourite(key: String): Boolean {

        return if (favourites == null) {
            false
        } else {
            favourites!!.contains(key)
        }
    }

    override fun maStart() {


    }

    override fun maStop() {

    }


}
