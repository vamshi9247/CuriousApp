package com.example.vamshi.homwayprojectchallenge;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.QueryConstants;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;


/**
 * Created by vamshi on 4/18/18.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

     private boolean mLocPermGranted = false;
     private GoogleMap map;
     ArrayList<Placesinfo> places ;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);


        Intent intent = getIntent();
        places= intent.getExtras().getParcelableArrayList(QueryConstants.Mainbundlekey);


        FragmentManager MapFragment = getSupportFragmentManager();
        SupportMapFragment smp =(SupportMapFragment) MapFragment.findFragmentById(R.id.map);
        smp.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map =googleMap;
        ClusterManager mClusterPlacesManager = new ClusterManager<Placesinfo>(this,map);
        mClusterPlacesManager.addItems(places);
        mClusterPlacesManager.setAnimation(true);
        map.setOnCameraIdleListener(mClusterPlacesManager);
        map.setOnMarkerClickListener(mClusterPlacesManager);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(QueryConstants.DEFAULTPLACE,12));


    }


    @Override
    protected void onStart() {
        super.onStart();

        String[] permissions = { Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};


        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mLocPermGranted =true;

            Log.i("#######", "getPermission: yes");

        } else {

            ActivityCompat.requestPermissions((Activity) this, permissions, 1234);


        }


    }


//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onRestoreInstanceState(savedInstanceState, persistentState);
//        if(savedInstanceState!=null){
//            places = savedInstanceState.getParcelableArrayList(QueryConstants.Mainbundlekey);
//            Log.i("bundle saved", "onRestoreInstanceState: "+ places + savedInstanceState.containsKey(QueryConstants.Mainbundlekey));
//        }
//
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        outState.putParcelableArrayList(QueryConstants.Mainbundlekey,places);
//        super.onSaveInstanceState(outState, outPersistentState);
//
//
//
//    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1234:
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                        mLocPermGranted = false;
                        Log.i("#######", "getPermission: no");
                        return;
                    }else{
                        mLocPermGranted =true;

                    }

        }


    }



}
