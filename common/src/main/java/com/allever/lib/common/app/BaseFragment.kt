package com.allever.lib.common.app

import android.os.Bundle
import android.support.v4.app.Fragment
import com.allever.lib.common.util.DLog

open class BaseFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DLog.d(this::class.java.simpleName)
    }
}