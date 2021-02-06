package com.sweetmay.nasa.model.repo

import com.sweetmay.nasa.model.entity.APOD
import com.sweetmay.nasa.model.entity.EarthPicData
import com.sweetmay.nasa.model.entity.rover.MarsPhotoData
import io.reactivex.rxjava3.core.Single

interface INasaRepo {
    fun getApod(apiKey: String): Single<APOD>
    fun getImageUrl(apiKey: String, epicUrl: String): Single<EarthPicData>
    fun getRoverImage(apiKey: String, camera: String): Single<MarsPhotoData>
}