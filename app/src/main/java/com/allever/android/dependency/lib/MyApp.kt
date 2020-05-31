package com.allever.android.dependency.lib

import com.allever.lib.common.app.App
import com.allever.lib.recommend.RecommendGlobal

class MyApp: App() {
    override fun onCreate() {
        super.onCreate()
        RecommendGlobal.init("baidu")
    }
}