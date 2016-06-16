package com.ye.studyapp.base;

import android.os.Bundle;


public abstract class MvpActivity<P extends BasePresenter> extends BaseTitleBarActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);

        initData();
    }

    protected abstract P createPresenter();

    protected void initData(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
