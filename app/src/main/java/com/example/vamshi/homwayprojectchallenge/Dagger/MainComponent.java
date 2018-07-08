package com.example.vamshi.homwayprojectchallenge.Dagger;

import com.example.vamshi.homwayprojectchallenge.MainActivity;
import com.example.vamshi.homwayprojectchallenge.Model.PreferencesFactory;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.RetrofitApiClient;


import javax.inject.Singleton;

import dagger.Component;

@MyAppScope
@Component(modules = {RetrofitApiClient.class, PreferencesFactory.class})
public interface MainComponent {

    void Inject(MainActivity mainActivity);

}
