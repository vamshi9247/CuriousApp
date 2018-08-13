package com.example.vamshi.Curious.Screens.PlacedetailsScreen

import android.util.Log
import com.example.vamshi.Curious.Model.Retrofit.JsonPlacePics
import com.example.vamshi.Curious.PhotoEntity
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import retrofit2.Response

class PhotoObserver : Observer<Response<JsonPlacePics>> {

    var photoList: MutableList<PhotoEntity> = mutableListOf()


    override fun onSubscribe(d: Disposable) {

        photoList.add(PhotoEntity())

    }

    override fun onNext(t: Response<JsonPlacePics>) {

        var photo = PhotoEntity()

        var rPhoto = t.body()?.response?.photos?.items

        rPhoto?.listIterator()?.forEach {
            var photo = PhotoEntity()
            photo.prefix = it.prefix
            photo.suffix = it.suffix
            photoList.add(photo)
        }

        Log.i("photoObserver", "${t.isSuccessful} and body is  ${t.body()} and response is ${t}")
        Log.i("photoObserver", "message is ${t.message()} and error is ${t.errorBody()} ")
    }


    override fun onError(e: Throwable) {
        Log.i("photoObserver", e.toString())
    }

    override fun onComplete() {
        if (photoList.size != 0) { EventBus.getDefault().post(photoList) }
    }


}


