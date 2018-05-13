package com.example.vamshi.homwayprojectchallenge.Presenter;

import android.content.SharedPreferences;

import com.example.vamshi.homwayprojectchallenge.ContractPresenterView;
import com.example.vamshi.homwayprojectchallenge.Model.MyModel;
import com.google.gson.Gson;

public class  MyPresenterMain implements ContractPresenterView.PresenterMainWork,ContractPresenterModel.PresenterWork{

    ContractPresenterModel.ModelWork myModel = new MyModel(this);

    SharedPreferences favourites;

    public MyPresenterMain(SharedPreferences favourites) {
        this.favourites = favourites;
    }

    @Override
    public void getPlaces(String s) {

        myModel.requestCall(s);

    }


    @Override
    public void addFavourite(String key) {

        SharedPreferences.Editor spEdit = favourites.edit();
        spEdit.putBoolean(key,true);
        spEdit.commit();

    }

    @Override
    public void removeFavouite(String key) {
        SharedPreferences.Editor spEdit =favourites.edit();
        spEdit.remove(key);
        spEdit.commit();

    }

    @Override
    public Boolean isFavourite(String key) {

        if(favourites==null){
            return false;
        }else{
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
