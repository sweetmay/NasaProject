package com.sweetmay.nasa.view

import com.sweetmay.nasa.model.entity.APOD
import com.sweetmay.nasa.view.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface APODView: BaseView {
    fun showApod(apod: APOD)
    fun setTitle()
}