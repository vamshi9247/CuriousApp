package com.example.vamshi.Curious.Model.Retrofit;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteRx {

    String FOURSQUARE_BASE_URL = "https://api.foursquare.com/";

    @GET("v2/venues/search")
    Observable<Response<JsonOutput>>
    QueryPlaceDetails(@Query("ll") String ll,
                      @Query("intent") String checkin,
                      @Query("limit") int limit,
                      @Query("radius") String radius,
                      @Query("query") String query,
                      @Query("client_id") String client_key,
                      @Query("client_secret") String client_secret,
                      @Query("v") String yyyymmdd);


//      https://api.foursquare.com/v2/venues/search?ll=Seattle,WA&
//      radius=100000&
//      query=temple&
//      client_id=TXU3LXIDMFX2GNDK3A1AHYIATBUTFI12H4HWDMPGTP5AQXIZ&
//      client_secret=YJDUWJJ4RBWNKJU1OCWO2ITSS5WPYT1GX4ZW52YJUBD3JIHI&v=20180426

    @GET("v2/venues/{place_id}/photos")
    Observable<Response<JsonPlacePics>>
    QueryPlacePhotos(@Path("place_id") String url,
                     @Query("client_id") String client_key,
                     @Query("client_secret") String client_secret,
                     @Query("v") String yyyymmdd);

    // "https://api.foursquare.com/v2/venues/4bafdeccf964a520a8273ce3/photos?client_id=TXU3LXIDMFX2GNDK3A1AHYIATBUTFI12H4HWDMPGTP5AQXIZ&client_secret=YJDUWJJ4RBWNKJU1OCWO2ITSS5WPYT1GX4ZW52YJUBD3JIHI&v=20180426"

}