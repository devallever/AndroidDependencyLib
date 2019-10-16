package com.allever.lib.common.util

import android.widget.Toast
import com.allever.lib.common.app.App
import com.allever.lib.common.util.log.LogUtils
import org.jetbrains.anko.runOnUiThread

fun Any.log(msg: String) {
    LogUtils.d(msg)
}

fun Any.loge(msg: String) {
    LogUtils.e(msg)
}

fun Any.toast(msg: String) {
    App.context.runOnUiThread {
        Toast.makeText(App.context, msg, Toast.LENGTH_SHORT).show()
    }
}

fun Any.getString(resId: Int): String {
    return App.context.resources.getString(resId)
}