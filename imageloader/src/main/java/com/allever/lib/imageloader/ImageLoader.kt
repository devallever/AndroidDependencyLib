package com.allever.lib.imageloader

import android.content.Context
import android.widget.ImageView
import java.io.File

object ImageLoader {

    private var mProxy: ImageLoaderProxy? = DefaultImageLoaderProxy

    fun setProxy(proxy: ImageLoaderProxy) {
        mProxy = proxy
    }

    fun loadImage(context: Context, url: String, imageView: ImageView) {
        mProxy?.loadImage(context, url, imageView)
    }

    fun loadImage(context: Context, file: File, imageView: ImageView) {
        mProxy?.loadImage(context, file, imageView)
    }

    fun loadImage(context: Context, resId: Int, imageView: ImageView) {
        mProxy?.loadImage(context, resId, imageView)
    }

}