package com.example.vamshi.Curious.Screens.MapScreen;

import android.Manifest;
import android.app.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.vamshi.Curious.Model.Retrofit.QueryConstants;

import com.example.vamshi.Curious.PlacesEntity;
import com.example.vamshi.Curious.R;
import com.example.vamshi.Curious.Screens.PlacedetailsScreen.PlaceDetails;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;

/**
 * Created by vamshi on 4/18/18.
 */

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private boolean mLocPermGranted = false;

    private GoogleMap map;
    ArrayList<PlacesEntity> places;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        Intent intent = getIntent();
        places = intent.getExtras().getParcelableArrayList(QueryConstants.Mainbundlekey);

        FragmentManager MapFragment = getSupportFragmentManager();
        SupportMapFragment smp = (SupportMapFragment) MapFragment.findFragmentById(R.id.map);
        smp.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        boolean success = map.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                this, R.raw.mapstyle_json));
        ClusterManager mClusterPlacesManager = new ClusterManager<PlacesEntity>(this, map);
        mClusterPlacesManager.addItems(places);
        mClusterPlacesManager.setAnimation(true);

        map.setOnInfoWindowClickListener( v-> {

            Intent intent = new Intent(MapActivity.this, PlaceDetails.class);

            startActivity(intent);
            Log.i("start activity place", "onMapReady: ");});


        map.setOnCameraIdleListener(mClusterPlacesManager);
        map.setOnMarkerClickListener(mClusterPlacesManager);
        map.getUiSettings().setZoomControlsEnabled(true);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(QueryConstants.CURRENT_PLACE, 8));

        Log.i("map style", "onMapReady: " + success);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocPermGranted = true;

            Log.i("#######", "getPermission: yes");
        } else {
            ActivityCompat.requestPermissions((Activity) this, permissions, 1234);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1234:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED ||
                        grantResults[1] != PackageManager.PERMISSION_GRANTED) {
                    mLocPermGranted = false;
                    Log.i("#######", "getPermission: no");
                    return;
                } else {
                    mLocPermGranted = true;
                }
        }
    }
}
