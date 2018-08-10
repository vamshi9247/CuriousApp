package com.example.vamshi.Curious.Model.Retrofit;


import com.example.vamshi.Curious.Dagger.Scopes.MyAppScope;
import com.example.vamshi.Curious.Dagger.Qualifiers.QFourSquareGetRequest;
import com.example.vamshi.Curious.Dagger.Qualifiers.QGoogleDistanceGetRequest;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitApiClient {

    @Provides
    @MyAppScope
    @QFourSquareGetRequest
    public RemoteRx callFourSquareClient() {
        RemoteRx fourSquareApiCall = new Retrofit.Builder()
                .baseUrl(RemoteRx.FOURSQUARE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RemoteRx.class);
        return fourSquareApiCall ;
    }

    @Provides
    @MyAppScope
    @QGoogleDistanceGetRequest
    public RemoteRx callGoogleClient(){
        RemoteRx googleDistanceApiCall = new Retrofit.Builder()
                .baseUrl(RemoteRx.GOOGLE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RemoteRx.class);
        return googleDistanceApiCall;
    }


}
