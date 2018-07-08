package com.example.vamshi.homwayprojectchallenge.Presenter;

import android.content.SharedPreferences;

import com.example.vamshi.homwayprojectchallenge.Model.CallObserver;
import com.example.vamshi.homwayprojectchallenge.Model.JsonOutput;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.QueryConstants;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.RemoteRx;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MyPresenterMain implements ContractPresenterView.PresenterMainWork {


    SharedPreferences favourites;
    RemoteRx callClient;


    @Inject
    public MyPresenterMain(RemoteRx callClient, SharedPreferences favourites) {
        this.favourites = favourites;
        this.callClient = callClient;
    }

    @Override
    public void getPlaces(String query) {

        Observable<Response<JsonOutput>> observable = callClient.QueryPlaceDetails(
                QueryConstants.NEAR,
                QueryConstants.MATCH,
                QueryConstants.RADIUS,
                query,
                QueryConstants.FOURSQUARE_CLIENT_KEY,
                QueryConstants.FOURSQUARE_CLIENT_SECRET,
                QueryConstants.YYYMMDD);


        Observer<Response<JsonOutput>> observer = new CallObserver();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    @Override
    public void addFavourite(String key) {

        SharedPreferences.Editor spEdit = favourites.edit();
        spEdit.putBoolean(key, true);
        spEdit.commit();

    }

    @Override
    public void removeFavourite(String key) {
        SharedPreferences.Editor spEdit = favourites.edit();
        spEdit.remove(key);
        spEdit.commit();

    }

    @Override
    public Boolean isFavourite(String key) {

        if (favourites == null) {
            return false;
        } else {
            return favourites.contains(key);
        }

    }

    @Override
    public void maStart() {


    }

    @Override
    public void maStop() {

    }


}
