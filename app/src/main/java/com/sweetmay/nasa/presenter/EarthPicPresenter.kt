package com.sweetmay.nasa.presenter

import android.util.Log
import com.sweetmay.nasa.App
import com.sweetmay.nasa.model.repo.INasaRepo
import com.sweetmay.nasa.view.EarthPicView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class EarthPicPresenter(private val repo: INasaRepo,
                        private val apiKey: String): MvpPresenter<EarthPicView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
            repo.getImageUrl(apiKey, App.base_url_epic).observeOn(AndroidSchedulers.mainThread()).subscribe{url->
                Log.d(javaClass.simpleName, url)
                viewState.setImage(url)
            }
        }
    }