package com.example.vamshi.homwayprojectchallenge.Model.Retrofit;

import com.example.vamshi.homwayprojectchallenge.Model.JsonOutput;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteRx {

    String BASE_URL = "https://api.foursquare.com/";

    @GET("v2/venues/search")
    Observable<Response<JsonOutput>> getPlaceDetailsForQuery(@Query("near") String location,
                                                             @Query("checkin") String ll,
                                                             @Query("radius") String radius,
                                                             @Query("query") String query,
                                                             @Query("client_id") String client_key,
                                                             @Query("client_secret") String client_secret,
                                                             @Query("v") String yyyymmdd);


    //  https://api.foursquare.com/v2/venues/search?ll=Seattle,WA&radius=100000&query=temple&client_id=TXU3LXIDMFX2GNDK3A1AHYIATBUTFI12H4HWDMPGTP5AQXIZ&client_secret=YJDUWJJ4RBWNKJU1OCWO2ITSS5WPYT1GX4ZW52YJUBD3JIHI&v=20180426

}