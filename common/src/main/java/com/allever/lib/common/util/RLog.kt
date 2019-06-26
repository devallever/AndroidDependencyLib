package com.allever.lib.common.util

import android.util.Log

object RLog {

    private val TAG = RLog::class.java.simpleName

    fun d(msg: String) {
        Log.d(TAG, msg)
    }

    fun e(msg: String) {
        Log.e(TAG, msg)
    }


}