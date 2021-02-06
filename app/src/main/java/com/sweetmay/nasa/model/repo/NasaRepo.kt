package com.sweetmay.nasa.model.repo

import com.sweetmay.nasa.model.entity.APOD
import com.sweetmay.nasa.model.entity.EarthPicData
import com.sweetmay.nasa.model.entity.rover.MarsPhotoData
import com.sweetmay.nasa.utils.ApiHolder
import com.sweetmay.nasa.utils.date.DateUtil
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class NasaRepo(private val apiHolder: ApiHolder, private val dateUtil: DateUtil): INasaRepo {

    override fun getApod(apiKey: String): Single<APOD> {
        return apiHolder.dataSourceCommon
            .getPOD(apiKey)
            .subscribeOn(Schedulers.io())
    }

    override fun getImageUrl(apiKey: String, epicUrl: String): Single<EarthPicData> {
        return getImageData(apiKey).map{ list ->
            val sb = StringBuilder()
            val data = list.first()
            data.url = sb.append(epicUrl)
                .append(dateUtil.getFormattedDateForEpic(data.date))
                .append("/png").append("/")
                .append(data.image)
                .append(".png")
                .toString()
            data
        }
    }

    private fun getImageData(apiKey: String): Single<List<EarthPicData>> {
        return apiHolder.dataSourceCommon
                .getEarthPicData(apiKey)
                .subscribeOn(Schedulers.io())
    }

    override fun getRoverImage(apiKey: String, camera: String)
    : Single<MarsPhotoData> {
        return apiHolder.dataSourceCommon
            .getMarsPic(dateUtil.getFormattedDateForRover(), camera, apiKey)
            .subscribeOn(Schedulers.io())
    }
}

