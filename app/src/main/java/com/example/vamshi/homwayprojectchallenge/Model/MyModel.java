package com.example.vamshi.homwayprojectchallenge.Model;

import com.example.vamshi.homwayprojectchallenge.Presenter.ContractPresenterModel;
import com.example.vamshi.homwayprojectchallenge.Retrofit.QueryConstants;
import com.example.vamshi.homwayprojectchallenge.Retrofit.RemoteRx;
import com.example.vamshi.homwayprojectchallenge.Retrofit.RetrofitApiClient;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class MyModel implements ContractPresenterModel.ModelWork {

    ContractPresenterModel.PresenterWork mPresenter;

    public MyModel(ContractPresenterModel.PresenterWork presenter) {
        mPresenter = presenter;
    }

    @Override
    public void requestCall(String query) {

        RetrofitApiClient retrofitApiClient = RetrofitApiClient.getInstance();
        RemoteRx remoteRx = retrofitApiClient.getEndPoint();
        Observable<Response<JsonOutput>> observable = remoteRx.getPlaceDetailsForQuery(
                QueryConstants.NEAR,
                QueryConstants.MATCH,
                QueryConstants.RADIUS,
                query,
                QueryConstants.FOURSQUARE_CLIENT_KEY,
                QueryConstants.FOURSQUARE_CLIENT_SECRET,
                QueryConstants.YYYMMDD);


        Observer<Response<JsonOutput>> observer = new CallObserver();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);



}

}




