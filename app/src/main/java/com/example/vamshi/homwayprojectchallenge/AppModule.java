package com.example.vamshi.homwayprojectchallenge;

import android.content.Context;

import com.example.vamshi.homwayprojectchallenge.Dagger.MyAppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    Context appContext;


    public AppModule(Context appContext) {

        this.appContext = appContext;
    }

    @Provides
    @MyAppScope
    public Context getApplication() {
        return appContext;
    }
}
