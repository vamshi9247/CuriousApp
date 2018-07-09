package com.example.vamshi.homwayprojectchallenge.Screens.MainScreen;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.vamshi.homwayprojectchallenge.Dagger.Qualifiers.QFourSquareGetRequest;
import com.example.vamshi.homwayprojectchallenge.Model.CallObserver;
import com.example.vamshi.homwayprojectchallenge.Model.JsonDistanceMatrix;
import com.example.vamshi.homwayprojectchallenge.Model.JsonOutput;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.QueryConstants;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.RemoteRx;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainPresenter implements ContractPresenterView.PresenterMainWork {


    SharedPreferences favourites;
    RemoteRx callClient;


    @Inject
    public MainPresenter(@QFourSquareGetRequest RemoteRx callClient,
                         SharedPreferences favourites) {
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



        observable.subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.computation())
                .subscribe(new CallObserver());
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
