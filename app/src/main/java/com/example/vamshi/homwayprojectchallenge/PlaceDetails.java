package com.example.vamshi.homwayprojectchallenge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.QueryConstants;
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

         String Url ="https://maps.googleapis.com/maps/api/staticmap?center=seattle,WA" +
                 "&zoom=10&" +
                 "size=200x200&" +
                 "markers=size:mid%7Clabel:S%7Ccolor:green%7C47.60,122.33"+
                 "maptype=roadmap&scale=2&" +
                 "key=" + QueryConstants.GMAPSSTATICKEY;

        Picasso.get().load(Url).into(staticMap);

    }
}
