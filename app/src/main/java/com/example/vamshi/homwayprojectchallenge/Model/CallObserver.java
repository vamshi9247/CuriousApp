package com.example.vamshi.homwayprojectchallenge.Model;

import android.util.Log;

import com.example.vamshi.homwayprojectchallenge.PlacesEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class CallObserver implements Observer<Response<JsonOutput>> {


    public ArrayList<PlacesEntity> place;
    private JsonOutput pojoresponse;


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Response<JsonOutput> response) {
        Log.i("Response", "onNext: " + response.toString());
        place = new ArrayList<>();

        Log.i("get link", "onNext: " + response.body());
        pojoresponse = response.body();

        List<Venues> venues = pojoresponse.getResponse().getVenues();
        Log.i("length", "onNext: " + venues.size());


        for (Venues venue : venues) {

            String name = venue.getName();
            int distance = venue.getLocation().getDistance();
            ArrayList<String> formattedAddress = venue.getLocation().getFormattedAddress();

            String address = "";
            for (String s : formattedAddress) {

                address = address + s + ",";
            }


            double lng = venue.getLocation().getLng();
            double lat = venue.getLocation().getLat();
            Icon icon = venue.getCategories().get(0).getIcon();
            String prefixicon = icon.getPrefix();
            String suffixicon = icon.getSuffix();
            String placeId = venue.getId();
            Log.i("Address", "onNext: " + address);
            place.add(new PlacesEntity(placeId, name, distance, address, lng, lat, prefixicon, suffixicon));
            Log.i("venues", "onNext: " + venues);
            Log.i("icon", "onNext: " + icon.getPrefix() + "bg_88" + icon.getSuffix());
        }

    }

    @Override
    public void onError(Throwable e) {
        Log.e("fourSquareApiObserver", "onError: no response ", e);

    }

    @Override
    public void onComplete() {

        EventBus.getDefault().post(place);
    }


}
