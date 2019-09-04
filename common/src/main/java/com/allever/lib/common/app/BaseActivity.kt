package com.allever.lib.common.app

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.allever.lib.common.R
import com.allever.lib.common.util.DLog
import com.allever.lib.common.util.ToastUtils
import com.quxianggif.util.ActivityCollector
import java.lang.ref.WeakReference

abstract class BaseActivity : AppCompatActivity() {
    protected val mHandler = Handler()
    private var mWeakRefActivity: WeakReference<Activity>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DLog.d(this.javaClass.simpleName)
        mWeakRefActivity = WeakReference(this)
        ActivityCollector.add(mWeakRefActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.remove(mWeakRefActivity)
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