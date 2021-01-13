package com.sweetmay.nasa.presenter

import com.sweetmay.nasa.model.repo.INasaRepo
import com.sweetmay.nasa.view.ui.APODView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class APODPresenter(val nasaRepo: INasaRepo,
                    val baseUrl: String,
                    val api_key: String): MvpPresenter<APODView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        nasaRepo.getApod(baseUrl, api_key)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ apod->
            viewState.showApod(apod)
        }
    }
}