package com.sweetmay.nasa.presenter

import com.sweetmay.nasa.model.repo.INasaRepo
import com.sweetmay.nasa.view.APODView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class APODPresenter(private val nasaRepo: INasaRepo,
                    val api_key: String): MvpPresenter<APODView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle()
        nasaRepo.getApod(api_key)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ apod->
            viewState.showApod(apod)
        }
    }
}