package com.example.vamshi.homwayprojectchallenge.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.vamshi.homwayprojectchallenge.AppModule;
import com.example.vamshi.homwayprojectchallenge.Dagger.MyAppScope;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.QueryConstants;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

@Module(includes = AppModule.class)
public class PreferencesFactory {

    @Provides
    @MyAppScope
    public SharedPreferences getPreferences(Context context) {

        SharedPreferences favourites = context.getSharedPreferences(
                QueryConstants.SHAREDFAVOURITEKEY,
                MODE_PRIVATE);

        return favourites;
    }
}
