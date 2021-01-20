package com.sweetmay.nasa.presenter

import android.util.Log
import com.sweetmay.nasa.App
import com.sweetmay.nasa.model.repo.INasaRepo
import com.sweetmay.nasa.view.EarthPicView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class EarthPicPresenter(private val repo: INasaRepo,
                        private val apiKey: String,
                        private val mainThreadScheduler: Scheduler): MvpPresenter<EarthPicView>() {

    private val compositeDisposable = CompositeDisposable()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()


        callRoverImages()

        viewState.showLoading()
        viewState.setTitle()
            val imageObservable = repo.getImageUrl(apiKey, App.BASE_URL_EPIC)
                    .observeOn(mainThreadScheduler)
                    .subscribe{imageData->
                        viewState.setImage(imageData.url)
                        viewState.setCaption(imageData.caption)
                    }
        compositeDisposable.add(imageObservable)
    }

    private fun callRoverImages(){
        val obs = repo.getRoverImage(apiKey, "fhaz").subscribeOn(mainThreadScheduler).subscribe{data->
            if(data.photos.isNotEmpty()){
                Log.d(javaClass.simpleName, data.photos.first().toString())
            }else {
                callRoverImages()
            }
        }
        compositeDisposable.add(obs)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}