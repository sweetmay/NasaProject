package com.sweetmay.nasa.utils.date


import android.util.Log
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class DateUtil {

    var days: Long = -1

    fun getFormattedDateForEpic(stringDate: String): String {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm:SS", Locale.ROOT)
        val date = LocalDate.parse(stringDate, dateFormat).toString()
        return date.replace("-", "/")
    }

    fun getFormattedDateForRover(): String{
        days++
        Log.d(javaClass.simpleName, days.toString())
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-DD", Locale.ROOT)
        return LocalDateEx.getNow().minusDays(days).format(dateFormat).toString()
    }
}