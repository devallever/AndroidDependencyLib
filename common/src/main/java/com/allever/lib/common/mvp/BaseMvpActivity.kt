package com.allever.lib.common.mvp

import android.os.Bundle
import com.allever.lib.common.app.BaseActivity

/**
 * Created by allever on 18-2-28.
 */

abstract class BaseMvpActivity<V, T : BasePresenter<V>> : BaseActivity() {

    protected var mPresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = createPresenter()
        mPresenter?.attachView(this as V) //view 与 Presenter 关联
    }

    override fun onDestroy() {
        mPresenter?.detachView()
        super.onDestroy()
    }

    protected abstract fun createPresenter(): T
}
