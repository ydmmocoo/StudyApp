package com.ye.studyapp.presenter;

import com.ye.studyapp.base.BasePresenter;
import com.ye.studyapp.model.CookBookDetailModel;
import com.ye.studyapp.rxjava.ApiCallback;
import com.ye.studyapp.rxjava.SubscriberCallBack;
import com.ye.studyapp.ui.iview.CookBookDetailView;

/**
 * Created by admin on 2016/6/16.
 */

public class CookBookDetailPresenter extends BasePresenter<CookBookDetailView> {

    public CookBookDetailPresenter(CookBookDetailView view) {
        attachView(view);
    }


    public void loadData(String apiKey,int id) {
        mvpView.showLoading();
        addSubscription(apiStores.loadCookBookDetailData(apiKey,id),
                new SubscriberCallBack<>(new ApiCallback<CookBookDetailModel>() {
                    @Override
                    public void onSuccess(CookBookDetailModel model) {
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
