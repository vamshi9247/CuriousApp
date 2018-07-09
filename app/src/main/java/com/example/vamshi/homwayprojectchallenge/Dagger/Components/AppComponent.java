package com.example.vamshi.homwayprojectchallenge.Dagger.Components;

import android.content.SharedPreferences;

import com.example.vamshi.homwayprojectchallenge.Dagger.Qualifiers.QFourSquareGetRequest;
import com.example.vamshi.homwayprojectchallenge.Dagger.Qualifiers.QGoogleDistanceGetRequest;
import com.example.vamshi.homwayprojectchallenge.Dagger.Scopes.MyAppScope;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.RemoteRx;
import com.example.vamshi.homwayprojectchallenge.Model.PreferencesFactory;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.RetrofitApiClient;


import dagger.Component;

@MyAppScope
@Component(modules = {RetrofitApiClient.class, PreferencesFactory.class})
public interface AppComponent {

    SharedPreferences getPrefernces();

    @QFourSquareGetRequest
    RemoteRx FourSquareApi();

    @QGoogleDistanceGetRequest
    RemoteRx GoogleApi();

}
