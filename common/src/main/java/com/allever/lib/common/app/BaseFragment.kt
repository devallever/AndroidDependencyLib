package com.allever.lib.common.app

import android.os.Bundle
import com.allever.lib.common.util.DLog

open class BaseFragment : androidx.fragment.app.Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DLog.d(this::class.java.simpleName)
    }
}