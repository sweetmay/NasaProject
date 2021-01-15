package com.sweetmay.nasa.model.repo

import com.sweetmay.nasa.model.entity.APOD
import com.sweetmay.nasa.model.entity.EarthPicData
import com.sweetmay.nasa.utils.ApiHolder
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class NasaRepo(private val apiHolder: ApiHolder): INasaRepo {

    override fun getApod(apiKey: String): Single<APOD> {
        return apiHolder.dataSourceCommon
            .getPOD(apiKey)
            .subscribeOn(Schedulers.io())
    }

    override fun getImageUrl(apiKey: String, epicUrl: String): Single<String> {
        return getImageData(apiKey).map{ list ->
            val sb = StringBuilder()
            val data = list.first()
            sb.append(epicUrl)
                    .append(getFormattedDate(data.date))
                    .append("/png").append("/")
                    .append(data.image)
                    .append(".png")
                    .toString()
        }
    }

    private fun getImageData(apiKey: String): Single<List<EarthPicData>> {
        return apiHolder.dataSourceCommon.getEarthPicData(apiKey).subscribeOn(Schedulers.io())
    }

    private fun getFormattedDate(stringDate: String): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm:SS", Locale.ROOT)
        val date = LocalDate.parse(stringDate, dateFormat).toString()
        return date.replace("-", "/")
    }
}

