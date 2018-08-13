package com.example.vamshi.Curious.Model.Retrofit;

import com.google.android.gms.maps.model.LatLng;

public class QueryConstants {

    public static final String FOURSQUARE_CLIENT_KEY = "TXU3LXIDMFX2GNDK3A1AHYIATBUTFI12H4HWDMPGTP5AQXIZ";
    public static final String FOURSQUARE_CLIENT_SECRET = "YJDUWJJ4RBWNKJU1OCWO2ITSS5WPYT1GX4ZW52YJUBD3JIHI";

    public static final String UNITS = "imperial";
    public static final String RADIUS ="50000";
    public static final String YYYMMDD ="20180426";
    public static final String IMAGE_SIZE ="88";
    public static final String INTENT="checkin";
    public static final int limit = 5;
    public static LatLng CURRENT_PLACE = new LatLng(47.6,-122.3);

    public static String STATIC_MAP_URL= "https://maps.googleapis.com/maps/api/staticmap?center=seattle,WA&" +
            "zoom=12&" +
            "format=png" +
            "style=feature:administrative.neighborhood%7Cvisibility:off&style=feature:poi%7Celement:labels.text%7Cvisibility:off&style=feature:road%7Cvisibility:off&style=feature:road%7Celement:labels%7Cvisibility:off&style=feature:water%7Celement:labels.text%7Cvisibility:off&size=480x360&" +
            "size=200x200&" +
            "markers=size:mid%7Clabel:S%7Ccolor:green%7C47.60,122.33&" +
            "maptype=roadmap&scale=2&" +
            "key=" + QueryConstants.GMAPSSTATICKEY;

    public static final String GMAPSSTATICKEY = "AIzaSyCkXHNdyvnFE6U7Orp4Hw5wSJeK0TzjKZc";

    public static final String SHAREDFAVOURITEKEY = "favourite";
    public static final String Mainbundlekey = "placeBundle ";

}

//https://igx.4sqi.net/img/general/300*500/lgHvCyoFfN78Bj7jKegrAIR9hBk8SqLf245S7Tinylw.jpg