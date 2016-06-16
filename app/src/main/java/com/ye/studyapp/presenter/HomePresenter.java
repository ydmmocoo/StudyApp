package com.ye.studyapp.presenter;

import com.socks.library.KLog;
import com.ye.studyapp.base.BasePresenter;
import com.ye.studyapp.model.HomeModel;
import com.ye.studyapp.rxjava.ApiCallback;
import com.ye.studyapp.rxjava.SubscriberCallBack;
import com.ye.studyapp.ui.iview.HomeView;

/**
 * Created by admin on 2016/6/14.
 */

public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(HomeView view) {
        attachView(view);
    }


    public void loadData(String apiKey,int page) {
        mvpView.showLoading();
        addSubscription(apiStores.loadData(apiKey,page),
                new SubscriberCallBack<>(new ApiCallback<HomeModel>() {
                    @Override
                    public void onSuccess(HomeModel model) {
                        mvpView.getDataSuccess(model);
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                }));
    }

}
