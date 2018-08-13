package com.example.vamshi.Curious.Screens.PlacedetailsScreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import butterknife.ButterKnife
import com.example.vamshi.Curious.*
import com.example.vamshi.Curious.Dagger.Components.DaggerPlaceDetailComponent
import com.example.vamshi.Curious.Dagger.PlaceDetailModule
import com.example.vamshi.Curious.Model.Retrofit.QueryConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.place_details.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


/**
 * Created by vamshi on 4/18/18.
 */

class PlaceDetails : AppCompatActivity() {

    private lateinit var place: PlacesEntity

    @Inject
    lateinit var PDPresenter: PlaceDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.place_details)
        ButterKnife.bind(this)

        place = intent.extras!!.getParcelable(QueryConstants.MAIN_INTENT_DETAIL)

        injectDependencies()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        PDPresenter.getPhotos()

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun getPhotoList(placePhoto: Pics) {
        collapsing_toolbar.title = place.name
        place.pics = placePhoto
        if (placePhoto.photo?.size!! < 2 ) {
            Picasso.get().load(place.mapUrl).into(staticmap)
            Log.i("photo map url", "${place.mapUrl}")
        } else {
            var url = place.pics.photo?.get(1)?.toUrl()
            Log.i("photo url", "${url}")
            Picasso.get().load(url).into(staticmap)
        }
    }

    private fun injectDependencies() {

        DaggerPlaceDetailComponent.builder()
                .appComponent((application as MyApp).appComponent)
                .placeDetailModule(PlaceDetailModule(place, this))
                .build()
                .Inject(this)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }


}
