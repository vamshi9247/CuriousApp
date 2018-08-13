package com.example.vamshi.Curious;

import android.app.Application;

import com.example.vamshi.Curious.Dagger.AppContextModule;
import com.example.vamshi.Curious.Dagger.Components.AppComponent;
import com.example.vamshi.Curious.Dagger.Components.DaggerAppComponent;
import com.example.vamshi.Curious.Model.PreferencesFactory;
import com.example.vamshi.Curious.Model.Retrofit.RetrofitApiClient;


public class MyApp extends Application {

   private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
       appComponent= DaggerAppComponent.builder()
                .retrofitApiClient(new RetrofitApiClient())
                .appContextModule(new AppContextModule(this))
                .preferencesFactory(new PreferencesFactory())
                .build();

    }

   public AppComponent getAppComponent(){
         return appComponent;
    }


}
