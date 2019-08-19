package com.allever.android.dependency.lib.mvp;

import com.allever.lib.common.mvp.BasePresenter;

public interface MainContract {
    interface View {
        void updateView(String content);
    }

    interface Presenter<T extends BasePresenter<View>> {
        void getData();
    }
}
