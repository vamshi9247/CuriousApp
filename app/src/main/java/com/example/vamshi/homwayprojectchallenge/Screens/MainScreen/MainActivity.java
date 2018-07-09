package com.example.vamshi.homwayprojectchallenge.Screens.MainScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import com.example.vamshi.homwayprojectchallenge.Dagger.Components.DaggerMainActivityComponent;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.QueryConstants;
import com.example.vamshi.homwayprojectchallenge.MyApp;
import com.example.vamshi.homwayprojectchallenge.PlacesEntity;
import com.example.vamshi.homwayprojectchallenge.R;
import com.example.vamshi.homwayprojectchallenge.Screens.MapScreen.MapActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener {

    RecyclerView.Adapter adapter;

    ArrayList<PlacesEntity> places;

    @Inject
    MainPresenter mPresenter;

    //  @BindView(R.id.search)
    SearchView searchView;
    //   @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSavedInstanceState(savedInstanceState);

        injectDependencies();
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.search);
        recyclerView = findViewById(R.id.recyclerview);

        fab = findViewById(R.id.fabmap);
        fab.setOnClickListener((v) -> fabClick());

        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener( () ->  onClose() );

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlacesAdapter(places, this, mPresenter);
        recyclerView.setAdapter(adapter);

    }


    private void injectDependencies() {
        ButterKnife.bind(this);
        DaggerMainActivityComponent
                .builder()
                .appComponent(((MyApp) getApplication()).getAppComponent())
                .build()
                .Inject(this);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        mPresenter.getPlaces(query);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }


    public boolean onClose() {
        if (places != null) {
            places.clear();
        }
        adapter = new PlacesAdapter(places, this, mPresenter);
        recyclerView.setAdapter(adapter);
        return false;
    }

    //  @OnClick(R.id.fabmap)
    void fabClick() {

        Intent startMap = new Intent(this, MapActivity.class);
        startMap.putParcelableArrayListExtra(QueryConstants.Mainbundlekey, places);
        startActivity(startMap);
        Toast.makeText(this, "Map", Toast.LENGTH_SHORT).show();

    }

    void getSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            places = savedInstanceState.
                    getParcelableArrayList(QueryConstants.Mainbundlekey);
            Log.i("bundle saved", "onRestoreInstanceState: " +
                    places +
                    savedInstanceState.containsKey(QueryConstants.Mainbundlekey));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.maStart();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(QueryConstants.Mainbundlekey, places);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.maStop();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    // hello this is for git testing part
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changePlacesAdapter(ArrayList<PlacesEntity> placeEvent) {

        places = placeEvent;
        adapter = new PlacesAdapter(places, this, mPresenter);
        recyclerView.setAdapter(adapter);

        Log.i("CHANGE PLACE ADAPTER", "changePlacesAdapter: yes");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        getSavedInstanceState(savedInstanceState);
    }
}