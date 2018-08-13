package com.example.vamshi.Curious;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.vamshi.Curious.Model.Retrofit.QueryConstants;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.util.ArrayList;


public class PlacesEntity implements ClusterItem, Parcelable {

    public PlacesEntity(String placeId, String name, int distance, String formattedAddress, double lng, double lat, String prefixicon, String suffixicon) {
        this.name = name;
        this.distance = distance;
        this.formattedAddress = formattedAddress;
        this.lng = lng;
        this.lat = lat;
        this.prefixicon = prefixicon;
        this.suffixicon = suffixicon;
        this.placeId = placeId;
    }


    private String name;
    private int distance;
    private String formattedAddress;
    private double lng;
    private double lat;
    private String prefixicon;
    private String suffixicon;
    private String placeId;
    private Pics pics;
    private String mapUrl ;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.distance);
        dest.writeString(this.formattedAddress);
        dest.writeDouble(this.lng);
        dest.writeDouble(this.lat);
        dest.writeString(this.prefixicon);
        dest.writeString(this.suffixicon);
        dest.writeString(this.placeId);

    }


    public static final Parcelable.Creator<PlacesEntity> CREATOR = new Parcelable.Creator<PlacesEntity>() {
        public PlacesEntity createFromParcel(Parcel in) {
            return new PlacesEntity(in);
        }

        public PlacesEntity[] newArray(int size) {
            return new PlacesEntity[size];
        }
    };


    private PlacesEntity(Parcel in) {

        this.name = in.readString();
        this.distance = in.readInt();
        this.formattedAddress = in.readString();
        this.lng = in.readDouble();
        this.lat = in.readDouble();
        this.prefixicon = in.readString();
        this.suffixicon = in.readString();
        this.placeId = in.readString();


    }

    @Override
    public LatLng getPosition() {
        LatLng latLng = new LatLng(lat, lng);
        return latLng;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getSnippet() {
        return formattedAddress;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getFormattedAddress() {

        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getPrefixicon() {
        return prefixicon;
    }

    public void setPrefixicon(String prefixicon) {
        this.prefixicon = prefixicon;
    }

    public String getSuffixicon() {
        return suffixicon;
    }

    public void setSuffixicon(String suffixicon) {
        this.suffixicon = suffixicon;
    }

    public Pics getPics() {
        return pics;
    }

    public void setPics(Pics pics) {
        this.pics = pics;
    }

    public String getMapUrl() {
        this.mapUrl = "https://maps.googleapis.com/maps/api/staticmap?center="+lat+","+lng+"&"+
                "zoom=14&" +
                "format=jpg&" +
                "maptype=satellite&"+
                "size=300x300&" +
                "markers=color:red%7Clabel:"+name.toUpperCase().charAt(0)+"%7C+"+lat+","+lng+"&"+
                "scale=2&" +
                "key=" + QueryConstants.GMAPSSTATICKEY;

        return mapUrl;
    }



    @Override
    public String toString() {
        return super.toString();


    }


}


