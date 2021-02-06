package com.sweetmay.nasa.model.repo.api

import com.sweetmay.nasa.model.entity.APOD
import com.sweetmay.nasa.model.entity.EarthPicData
import com.sweetmay.nasa.model.entity.rover.MarsPhotoData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DataSourceCommon {
    @GET("planetary/apod")
    fun getPOD(@Query("api_key") apiKey: String): Single<APOD>
    @GET("EPIC/api/natural")
    fun getEarthPicData(@Query("api_key") apiKey: String): Single<List<EarthPicData>>
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getMarsPic(@Query("earth_date") earthDate: String,
                   @Query("camera") camera: String,
                   @Query ("api_key") apiKey: String): Single<MarsPhotoData>
}