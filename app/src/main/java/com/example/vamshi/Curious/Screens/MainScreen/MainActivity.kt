package com.example.vamshi.Curious.Screens.MainScreen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.widget.Toast
import com.example.vamshi.Curious.Dagger.Components.DaggerMainActivityComponent
import com.example.vamshi.Curious.Model.Retrofit.QueryConstants
import com.example.vamshi.Curious.MyApp
import com.example.vamshi.Curious.PlacesEntity
import com.example.vamshi.Curious.R
import com.example.vamshi.Curious.Screens.MapScreen.MapActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private var places: ArrayList<PlacesEntity> = ArrayList()

    @Inject
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSavedInstanceState(savedInstanceState)
        injectDependencies()
        setContentView(R.layout.activity_main)

        fab_map.setOnClickListener { fabClick() }
        search_view.setOnQueryTextListener(this)
        search_view.setOnCloseListener {  places?.clear() ; false }

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
        recycler_view.adapter =  PlacesAdapter(places, this, mPresenter)

        Log.i("CHANGE PLACE ADAPTER", "changePlacesAdapter: yes")
    }

    private fun getSavedInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.apply {
            places = this.getParcelableArrayList(QueryConstants.Mainbundlekey)  }

        Log.i("bundle saved", "onRestoreInstanceState: " + places +
                savedInstanceState?.containsKey(QueryConstants.Mainbundlekey))
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
}