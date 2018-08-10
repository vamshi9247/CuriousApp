package com.example.vamshi.Curious.Screens.PlacedetailsScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.vamshi.Curious.Model.Retrofit.QueryConstants;
import com.example.vamshi.Curious.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vamshi on 4/18/18.
 */

public class PlaceDetails extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.staticmap)
    ImageView staticMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.place_details);
         ButterKnife.bind(this);

        setSupportActionBar(toolbar);
         if(getSupportActionBar() != null){
             getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         }





         String Url ="https://maps.googleapis.com/maps/api/staticmap?center=seattle,WA&" +
                 "zoom=12&" +
                 "format=png"+
                 "style=feature:administrative.neighborhood%7Cvisibility:off&style=feature:poi%7Celement:labels.text%7Cvisibility:off&style=feature:road%7Cvisibility:off&style=feature:road%7Celement:labels%7Cvisibility:off&style=feature:water%7Celement:labels.text%7Cvisibility:off&size=480x360&"+
                 "size=200x200&" +
                 "markers=size:mid%7Clabel:S%7Ccolor:green%7C47.60,122.33&"+
                 "maptype=roadmap&scale=2&" +
                 "key=" + QueryConstants.GMAPSSTATICKEY;

        Picasso.get().load(Url).into(staticMap);

    }
}
