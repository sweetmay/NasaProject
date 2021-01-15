package com.sweetmay.nasa.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface BaseView: MvpView {
    fun showLoading()
    fun hideLoading()
}
