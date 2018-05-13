package com.example.vamshi.homwayprojectchallenge.Retrofit;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient  {

    private static RetrofitApiClient retrofitApiClient = null;

    private RemoteRx apiCall;

    private RetrofitApiClient(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RemoteRx.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        apiCall = retrofit.create(RemoteRx.class);
    }

    public static RetrofitApiClient getInstance(){
        if (retrofitApiClient == null){
            retrofitApiClient = new RetrofitApiClient();
        }
        return retrofitApiClient;
    }

   public RemoteRx getEndPoint(){
        return apiCall;
    }



}
