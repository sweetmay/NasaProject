package com.sweetmay.nasa.view

import com.sweetmay.nasa.model.entity.APOD
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface APODView: BaseView {
    fun showApodImage(apod: APOD)
    fun showApodVideo(apod: APOD)
    fun setTitle()
}