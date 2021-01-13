package com.sweetmay.nasa.model.repo

import com.sweetmay.nasa.model.entity.APOD
import io.reactivex.rxjava3.core.Single

interface INasaRepo {
    fun getApod(baseUrl: String, apiKey: String): Single<APOD>
}