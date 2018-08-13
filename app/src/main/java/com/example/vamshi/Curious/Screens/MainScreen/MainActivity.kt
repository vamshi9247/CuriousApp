package com.example.vamshi.Curious.Screens.MainScreen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.widget.Toast
import com.example.vamshi.Curious.Dagger.Components.DaggerMainActivityComponent
import com.example.vamshi.Curious.Model.Retrofit.QueryConstants
import com.example.vamshi.Curious.MyApp
import com.example.vamshi.Curious.PlacesEntity
import com.example.vamshi.Curious.R
import com.example.vamshi.Curious.Screens.MapScreen.MapActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject
import kotlin.math.round
import kotlin.math.truncate


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private var places: ArrayList<PlacesEntity> = ArrayList()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @Inject
    lateinit var mPresenter: MainPresenter

    private var mlocation: String = "47.6,-122.3"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSavedInstanceState(savedInstanceState)
        injectDependencies()
        if (checkPermission()) { findLocation() }
        setContentView(R.layout.activity_main)

        fab_map.setOnClickListener { fabClick() }
        search_view.setOnQueryTextListener(this)
        search_view.setOnCloseListener { places?.clear(); false }

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = PlacesAdapter(places, this, mPresenter)
    }

    private fun injectDependencies() {
        DaggerMainActivityComponent
                .builder()
                .appComponent((application as MyApp).appComponent)
                .build()
                .Inject(this)
    }

    override fun onQueryTextSubmit(query: String) = mPresenter.getPlaces(query)

    override fun onQueryTextChange(newText: String) = false

    private fun fabClick() {
        val startMap = Intent(this, MapActivity::class.java)
        startMap.putParcelableArrayListExtra(QueryConstants.Mainbundlekey, places)
        startActivity(startMap)
        Toast.makeText(this, "Map", Toast.LENGTH_SHORT).show()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun changePlacesAdapter(placeEvent: ArrayList<PlacesEntity>) {
        places = placeEvent
        recycler_view.adapter = PlacesAdapter(places, this, mPresenter)

        Log.i("CHANGE PLACE ADAPTER", "changePlacesAdapter: yes")
    }


    @SuppressLint("MissingPermission")
    fun findLocation(): Boolean {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? -> mPresenter.currentLocation(location) }
        return true
    }


    private fun getSavedInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.apply {
            places = this.getParcelableArrayList(QueryConstants.Mainbundlekey)

            Log.i("bundle saved", "onRestoreInstanceState: " + places +
                    savedInstanceState?.containsKey(QueryConstants.Mainbundlekey))
        }
    }

    override fun onStart() {
        super.onStart()
        mPresenter.maStart()
        EventBus.getDefault().register(this)
    }


    override fun onStop() {
        super.onStop()
        mPresenter.maStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(QueryConstants.Mainbundlekey, places)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        getSavedInstanceState(savedInstanceState)
    }


    fun checkPermission(): Boolean {

        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
        var granted = PackageManager.PERMISSION_GRANTED

        fun isAccessFineLocationEnabled(): Boolean {
            return ContextCompat.checkSelfPermission(this, permissions.get(0)) == granted
        }

        fun isAccessCoarseLocationEnabled(): Boolean {
            return ContextCompat.checkSelfPermission(this, permissions.get(1)) == granted
        }

        if (isAccessCoarseLocationEnabled() && isAccessFineLocationEnabled()) {
            return true
        } else {
            ActivityCompat.requestPermissions(this as Activity, permissions, 1234)
        }

        return false

    }


    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1234 -> if (grantResults[0] != PackageManager.PERMISSION_GRANTED || grantResults[1] != PackageManager.PERMISSION_GRANTED) {

                Log.i("#######", "getPermission:yes")

            } else {

                findLocation()
            }
        }
    }
}