package com.example.vamshi.homwayprojectchallenge.Model;

import android.app.Application;

import android.content.SharedPreferences;

import com.example.vamshi.homwayprojectchallenge.AppModule;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.QueryConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

@Module (includes = AppModule.class)
public class PreferencesFactory {

    @Provides
    @Singleton
    public SharedPreferences getPreferences(Application context) {

        SharedPreferences favourites =context.getSharedPreferences(QueryConstants.SHAREDFAVOURITEKEY,MODE_PRIVATE);

        return favourites;
    }
}
