package com.allever.lib.common.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.allever.lib.common.util.log.LogUtils

open class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}