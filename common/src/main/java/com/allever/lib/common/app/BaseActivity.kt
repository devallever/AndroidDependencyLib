package com.allever.lib.common.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.allever.lib.common.util.DLog

open class BaseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DLog.d(this.javaClass.simpleName)
    }
}