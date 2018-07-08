package com.example.vamshi.homwayprojectchallenge.Model.Retrofit;


import com.example.vamshi.homwayprojectchallenge.Dagger.MyAppScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitApiClient {

    @Provides
    @MyAppScope
    public RemoteRx callClient() {
        RemoteRx apiCall = new Retrofit.Builder()
                .baseUrl(RemoteRx.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RemoteRx.class);
        return apiCall;
    }


}
