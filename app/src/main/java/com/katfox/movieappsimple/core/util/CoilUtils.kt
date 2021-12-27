package com.katfox.movieappsimple.core.util

import android.widget.ImageView
import coil.load

fun String?.toImage() = "https://image.tmdb.org/t/p/w500${this}"

fun loadWithCoil(imageView: ImageView, imageUrl: String) {
    imageView.load(imageUrl) {
        crossfade(true)
        crossfade(1000)
    }
}