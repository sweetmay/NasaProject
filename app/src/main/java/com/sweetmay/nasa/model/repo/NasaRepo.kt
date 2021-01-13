package com.sweetmay.nasa.model.repo

import com.sweetmay.nasa.App
import com.sweetmay.nasa.model.entity.APOD
import com.sweetmay.nasa.utils.ApiHolder
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class NasaRepo: INasaRepo {

    override fun getApod(baseUrl: String, apiKey: String): Single<APOD> {
        return ApiHolder(baseUrl)
            .dataSource.getPOD(apiKey)
            .subscribeOn(Schedulers.io())
    }
}