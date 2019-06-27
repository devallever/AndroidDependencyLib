package com.allever.lib.common.app

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.allever.lib.common.util.DLog

open class BaseActivity: AppCompatActivity() {
    protected val mHandler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DLog.d(this.javaClass.simpleName)
    }
}