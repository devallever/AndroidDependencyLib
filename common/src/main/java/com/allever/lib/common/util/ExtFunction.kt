package com.allever.lib.common.util

import android.util.Log
import android.widget.Toast
import com.allever.lib.common.app.App

fun Any.log(msg: String) {
    Log.d("", msg)
}

fun Any.toast(msg: String) {
    Toast.makeText(App.context, msg, Toast.LENGTH_SHORT).show()
}