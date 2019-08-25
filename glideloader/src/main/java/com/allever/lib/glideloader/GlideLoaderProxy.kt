//package com.allever.lib.glideloader
//
//import android.content.Context
//import android.widget.ImageView
//import com.allever.lib.imageloader.ImageLoaderProxy
//import com.bumptech.glide.Glide
//import java.io.File
//
//class GlideLoaderProxy: ImageLoaderProxy {
//    override fun resumeRequest(context: Context) {
//        Glide.with(context).resumeRequests()
//    }
//
//    override fun stopRequest(context: Context) {
//        Glide.with(context).pauseRequests()
//    }
//
//    override fun loadImage(context: Context, url: String, imageView: ImageView) {
//        Glide.with(context).load(url).into(imageView)
//    }
//
//    override fun loadImage(context: Context, file: File, imageView: ImageView) {
//        Glide.with(context).load(file).into(imageView)
//    }
//
//    override fun loadImage(context: Context, resId: Int, imageView: ImageView) {
//        Glide.with(context).load(resId).into(imageView)
//    }
//}