package com.allever.android.dependency.lib.mvp

import com.allever.lib.common.mvp.BasePresenter

class MainPresenter : BasePresenter<MainView>() {
    fun getData() {
        mViewRef.get()?.updateView("content")
    }
}
