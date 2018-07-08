package com.example.vamshi.homwayprojectchallenge;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    Application application;


    public AppModule() {
        this.application = getApplication();
    }

    @Provides
    @Singleton
    public Application getApplication() {
        return application;
    }
}
