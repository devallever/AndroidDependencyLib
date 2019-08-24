package com.allever.lib.common.app

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.allever.lib.common.R
import com.allever.lib.common.util.DLog
import com.allever.lib.common.util.ToastUtils

abstract class BaseActivity: AppCompatActivity() {
    protected val mHandler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DLog.d(this.javaClass.simpleName)
    }

    private var firstPressedBackTime = 0L
    protected fun checkExit() {
        if (System.currentTimeMillis() - firstPressedBackTime < 2000) {
            super.onBackPressed()
        } else {
            ToastUtils.show(getString(R.string.click_again_to_exit))
            firstPressedBackTime = System.currentTimeMillis()
        }
    }
}