package com.allever.lib.common.util

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore

object SystemUtils {
    /**
     * 从相册中选择一张图片后， 回调Activity的onActivityResult方法进行处理
     */
    fun chooseImageFromGallery(activity: Activity, requestCode: Int) {
        val albumIntent = Intent(Intent.ACTION_PICK)
        albumIntent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        activity.startActivityForResult(albumIntent, requestCode)
    }
}