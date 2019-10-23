package com.allever.lib.common.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.allever.lib.common.util.DLog

open class BaseFragment : androidx.fragment.app.Fragment() {
    protected val mHandler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DLog.d(this::class.java.simpleName)
    }
}