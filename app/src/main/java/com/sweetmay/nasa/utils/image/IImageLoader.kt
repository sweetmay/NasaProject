package com.sweetmay.nasa.utils.image

import android.content.Context
import android.widget.ImageView

interface IImageLoader {
    fun loadImageIntoView(url: String, context: Context, imageView: ImageView, listener: OnImageSuccessListener)
}