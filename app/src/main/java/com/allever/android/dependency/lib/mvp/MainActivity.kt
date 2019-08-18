package com.allever.android.dependency.lib.mvp

import android.os.Bundle
import com.allever.android.dependency.lib.R
import com.allever.lib.common.mvp.BaseMvpActivity

class MainActivity : BaseMvpActivity<MainView, MainPresenter>(), MainView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun createPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun updateView(content: String) {

    }
}
