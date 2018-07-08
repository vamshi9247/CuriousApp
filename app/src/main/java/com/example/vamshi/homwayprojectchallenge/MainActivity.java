package com.example.vamshi.homwayprojectchallenge;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.RemoteRx;
import com.example.vamshi.homwayprojectchallenge.Presenter.ContractPresenterView;
import com.example.vamshi.homwayprojectchallenge.Presenter.MyPresenterMain;
import com.example.vamshi.homwayprojectchallenge.Model.Retrofit.QueryConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    RecyclerView.Adapter adapter;
    ContractPresenterView.PresenterMainWork mPresenter;
    ArrayList<Placesinfo> places ;


    @Inject
    SharedPreferences favourites;
    @Inject
    RemoteRx client;
    @BindView(R.id.search)
    SearchView searchView;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            places = savedInstanceState.
                    getParcelableArrayList(QueryConstants.Mainbundlekey);
            Log.i("bundle saved", "onRestoreInstanceState: " +
                    places +
                    savedInstanceState.containsKey(QueryConstants.Mainbundlekey));
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(QueryConstants.Mainbundlekey,places);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MyApp)getApplication()).getMainComponent().Inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MyPresenterMain(client,favourites);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlacesAdapter(places, this,mPresenter);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        mPresenter.getPlaces(query);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }

    @Override
    public boolean onClose() {
        if (places != null) {
            places.clear();
        }

        adapter = new PlacesAdapter(places, this,mPresenter);
        recyclerView.setAdapter(adapter);

        return false;
    }

    @OnClick(R.id.fabmap)
    void fabClick() {

        Intent startMap = new Intent(this, MapActivity.class);
        startMap.putParcelableArrayListExtra(QueryConstants.Mainbundlekey,places);
        startActivity(startMap);
        Toast.makeText(this, "Map", Toast.LENGTH_SHORT).show();

    }

    void saveBundle(Bundle outState){

        outState.putParcelableArrayList(QueryConstants.Mainbundlekey,places);
        Log.i("Bundle", "saveBundle: yes ");
    }

    void getBundle(Bundle savedInstanceState){
        if(savedInstanceState!=null){
            places = savedInstanceState.getParcelableArrayList(QueryConstants.Mainbundlekey);

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.maStart();
        EventBus.getDefault().register(this);
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
    public void changePlacesAdapter(ArrayList<Placesinfo> placeEvent) {

        places = placeEvent;
        adapter = new PlacesAdapter(places, this,mPresenter);
        recyclerView.setAdapter(adapter);

        Log.i("CHANGE PLACE ADAPTER", "changePlacesAdapter: yes");
    }


}
