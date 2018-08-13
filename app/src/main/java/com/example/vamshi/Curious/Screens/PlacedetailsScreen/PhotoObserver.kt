package com.example.vamshi.Curious.Screens.PlacedetailsScreen

import android.util.Log
import com.example.vamshi.Curious.Model.Retrofit.JsonPlacePics
import com.example.vamshi.Curious.PhotoEntity
import com.example.vamshi.Curious.Pics
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus
import retrofit2.Response

class PhotoObserver : Observer<Response<JsonPlacePics>> {

   lateinit var photoList: Pics


    override fun onSubscribe(d: Disposable) {


    }

    override fun onNext(t: Response<JsonPlacePics>) {
        var count = t.body()?.response?.photos?.count
        photoList = Pics(count);
        var photo = PhotoEntity()
        photoList.addPhoto(photo)

        var rPhoto = t.body()?.response?.photos?.items

        rPhoto?.listIterator()?.forEach {
            var photo = PhotoEntity()
            photo.prefix = it.prefix
            photo.suffix = it.suffix
//            it.user.photo.prefix
            photoList.photo?.add(photo)
        }

        Log.i("photoObserver", "${t.isSuccessful} and body is  ${t.body()} and response is ${t}")
        Log.i("photoObserver", "message is ${t.message()} and error is ${t.errorBody()} ")
    }


    override fun onError(e: Throwable) {
        Log.i("photoObserver", e.toString())
    }

    override fun onComplete() {
        if (photoList.photo?.size != 0) { EventBus.getDefault().post(photoList) }
    }


}


