package com.example.vamshi.Curious.Dagger.Components;

import android.content.SharedPreferences;

import com.example.vamshi.Curious.Dagger.Qualifiers.QFourSquareGetRequest;
import com.example.vamshi.Curious.Dagger.Qualifiers.QGoogleDistanceGetRequest;
import com.example.vamshi.Curious.Dagger.Scopes.MyAppScope;
import com.example.vamshi.Curious.Model.Retrofit.RemoteRx;
import com.example.vamshi.Curious.Model.PreferencesFactory;
import com.example.vamshi.Curious.Model.Retrofit.RetrofitApiClient;


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
