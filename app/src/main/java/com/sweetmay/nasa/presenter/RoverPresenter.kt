package com.sweetmay.nasa.presenter

import com.sweetmay.nasa.model.repo.INasaRepo
import com.sweetmay.nasa.view.RoverView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class RoverPresenter(private val repo: INasaRepo,
                     private val mainThreadScheduler: Scheduler,
                     private val apiKey: String): MvpPresenter<RoverView>() {

    private val compositeDisposable = CompositeDisposable()

    fun loadData(camera: String) {
        roverImages(camera)
    }

    private fun roverImages(camera: String){
        val obs = repo.getRoverImage(apiKey, camera)
            .observeOn(mainThreadScheduler)
            .subscribe{data->
            if(data.photos.isNotEmpty()){
                viewState.renderData(data.photos.first())
            }else {
                roverImages(camera)
            }
        }
        compositeDisposable.add(obs)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}