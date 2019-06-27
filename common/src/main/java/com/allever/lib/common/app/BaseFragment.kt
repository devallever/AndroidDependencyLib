package com.allever.lib.common.app

import android.os.Bundle
import android.support.v4.app.Fragment
import com.allever.lib.common.util.RLog

open class BaseFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RLog.d(this::class.java.simpleName)
    }
}