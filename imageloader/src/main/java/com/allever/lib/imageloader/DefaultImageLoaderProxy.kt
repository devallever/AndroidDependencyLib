package com.allever.lib.imageloader

import android.content.Context
import android.graphics.BitmapFactory
import android.widget.ImageView
import java.io.File

object DefaultImageLoaderProxy: ImageLoaderProxy{
    override fun loadImage(context: Context, url: String, imageView: ImageView) {

    }

    override fun loadImage(context: Context, file: File, imageView: ImageView) {
        imageView.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
    }

    override fun loadImage(context: Context, resId: Int, imageView: ImageView) {
        imageView.setImageResource(resId)
    }

}