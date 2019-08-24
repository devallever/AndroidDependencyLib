package com.allever.lib.common.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.allever.lib.common.app.BaseActivity;


/**
 * Created by allever on 18-2-28.
 */

public abstract class BaseMvpActivity<V, P extends BasePresenter<V>> extends BaseActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();
        mPresenter.attachView((V) this); //view 与 Presenter 关联
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    protected abstract P createPresenter();
}