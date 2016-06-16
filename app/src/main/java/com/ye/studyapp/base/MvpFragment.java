package com.ye.studyapp.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
        init();
    }

    protected abstract P createPresenter();

    /**
     * 网络请求
     */
    protected void init(){
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
