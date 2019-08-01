package com.allever.lib.imageloader

import android.content.Context
import android.widget.ImageView
import java.io.File

interface ImageLoaderProxy {
    fun loadImage(context: Context, url: String, imageView: ImageView)
    fun loadImage(context: Context, file: File, imageView: ImageView)
    fun loadImage(context: Context, resId: Int, imageView: ImageView)
    fun resumeRequest(context: Context)
    fun stopRequest(context: Context)
}