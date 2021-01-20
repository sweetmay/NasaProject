package com.sweetmay.nasa.view

import com.sweetmay.nasa.model.entity.rover.PhotoData
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RoverView: BaseView {
    fun renderData(photoData: PhotoData)
}