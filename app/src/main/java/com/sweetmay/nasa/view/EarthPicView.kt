package com.sweetmay.nasa.view

import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface EarthPicView: BaseView {
    fun setImage(url: String)
    fun setTitle()
    fun setCaption(caption: String)
}