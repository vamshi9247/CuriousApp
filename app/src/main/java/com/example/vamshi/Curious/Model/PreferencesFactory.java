package com.example.vamshi.Curious.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.vamshi.Curious.Dagger.AppContextModule;
import com.example.vamshi.Curious.Dagger.Scopes.MyAppScope;
import com.example.vamshi.Curious.Model.Retrofit.QueryConstants;

import dagger.Module;
import dagger.Provides;

import static android.content.Context.MODE_PRIVATE;

@Module(includes = AppContextModule.class)
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
