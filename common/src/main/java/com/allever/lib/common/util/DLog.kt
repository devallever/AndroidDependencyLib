package com.allever.lib.common.util

import android.util.Log
import com.allever.lib.common.BuildConfig

object DLog {

    private val TAG = DLog::class.java.simpleName

    fun d(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, msg)
        }
    }

    fun e(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg)
        }
    }
}