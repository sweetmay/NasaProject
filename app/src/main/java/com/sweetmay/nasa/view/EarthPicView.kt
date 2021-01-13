package com.sweetmay.nasa.view

import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface EarthPicView: BaseView {
    fun setImage(url: String)
}