package com.sweetmay.nasa.model.entity.rover

data class PhotoData(
        val camera: Camera,
        val earth_date: String,
        val id: Int,
        val img_src: String,
        val rover: Rover,
        val sol: Int
)