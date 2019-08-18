package com.allever.lib.common.mvp

import java.lang.ref.Reference
import java.lang.ref.WeakReference

/**
 * Created by allever on 18-2-28.
 */

abstract class BasePresenter<V> {
    protected var mViewRef: Reference<V>? = null//View类(Activity Fragment)接口弱引用

    protected val view: V?
        get() = mViewRef?.get()

    val isAttachedView: Boolean
        get() = mViewRef != null && mViewRef!!.get() != null

    fun attachView(view: V) {
        mViewRef = WeakReference(view)
    }

    fun detachView() {
        if (mViewRef != null) {
            mViewRef!!.clear()
            mViewRef = null
        }
    }
}
