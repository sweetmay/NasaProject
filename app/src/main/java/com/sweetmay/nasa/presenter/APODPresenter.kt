package com.sweetmay.nasa.presenter

import com.sweetmay.nasa.model.repo.INasaRepo
import com.sweetmay.nasa.view.APODView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

class APODPresenter(private val nasaRepo: INasaRepo,
                    private val api_key: String): MvpPresenter<APODView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading()
        viewState.setTitle()
        val apodObserver = nasaRepo.getApod(api_key)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ apod->
                viewState.hideLoading()
                if(apod.media_type == "video"){
                    viewState.showApodVideo(apod)
                }else {
                    viewState.showApodImage(apod)
                }
        }
        compositeDisposable.add(apodObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}