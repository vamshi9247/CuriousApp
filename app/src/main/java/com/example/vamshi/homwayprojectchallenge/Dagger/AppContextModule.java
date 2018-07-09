package com.example.vamshi.homwayprojectchallenge.Dagger;

import android.content.Context;

import com.example.vamshi.homwayprojectchallenge.Dagger.Scopes.MyAppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppContextModule {
    Context appContext;


    public AppContextModule(Context appContext) {

        this.appContext = appContext;
    }

    @Provides
    @MyAppScope
    public Context getApplication() {
        return appContext;
    }
}
