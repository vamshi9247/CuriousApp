package com.example.vamshi.homwayprojectchallenge;

import android.app.Application;

import com.example.vamshi.homwayprojectchallenge.Dagger.DaggerMainComponent;
import com.example.vamshi.homwayprojectchallenge.Dagger.MainComponent;
import com.example.vamshi.homwayprojectchallenge.Model.PreferencesFactory;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.RetrofitApiClient;


public class MyApp extends Application {


    private MainComponent mainComponent;


    @Override
    public void onCreate() {
        super.onCreate();
            mainComponent = DaggerMainComponent.builder()
                .retrofitApiClient(new RetrofitApiClient())
                    .preferencesFactory(new PreferencesFactory())
                .build();

    }

    public  MainComponent getMainComponent() {
        return mainComponent;
    }


}
