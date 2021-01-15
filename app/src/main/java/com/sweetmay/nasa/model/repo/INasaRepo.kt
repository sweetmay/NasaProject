package com.sweetmay.nasa.model.repo

import com.sweetmay.nasa.model.entity.APOD
import io.reactivex.rxjava3.core.Single

interface INasaRepo {
    fun getApod(apiKey: String): Single<APOD>
    fun getImageUrl(apiKey: String, epicUrl: String): Single<String>
}