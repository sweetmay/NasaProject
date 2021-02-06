package com.sweetmay.nasa.utils.image

import android.content.Context
import android.widget.ImageView
import coil.imageLoader
import coil.request.ImageRequest

class ImageLoader: IImageLoader {
    override fun loadImageIntoView(url: String,
                                   context: Context,
                                   imageView: ImageView,
                                   listener: OnImageSuccessListener) {
        val imgRequest = ImageRequest.Builder(context)
                .data(url)
                .target(onSuccess = {
                    listener.onSuccess()
                    imageView.setImageDrawable(it)
                }).build()
        context.imageLoader.enqueue(imgRequest)
    }

}