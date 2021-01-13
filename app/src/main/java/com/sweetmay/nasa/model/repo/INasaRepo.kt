package com.sweetmay.nasa.model.repo

import com.sweetmay.nasa.model.entity.APOD
import com.sweetmay.nasa.model.entity.EarthPicData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject

interface INasaRepo {
    fun getApod(apiKey: String): Single<APOD>
    fun getImageUrl(apiKey: String, epicUrl: String): PublishSubject<String>
}