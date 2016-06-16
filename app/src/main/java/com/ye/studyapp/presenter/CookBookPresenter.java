package com.ye.studyapp.presenter;

import com.ye.studyapp.base.BasePresenter;
import com.ye.studyapp.model.CookBookClassifyModel;
import com.ye.studyapp.rxjava.ApiCallback;
import com.ye.studyapp.rxjava.SubscriberCallBack;
import com.ye.studyapp.ui.iview.CookBookView;

/**
 * Created by admin on 2016/6/16.
 */

public class CookBookPresenter extends BasePresenter<CookBookView> {

    public CookBookPresenter(CookBookView view) {
        attachView(view);
    }


    public void loadData(String apiKey) {
        mvpView.showLoading();
        addSubscription(apiStores.loadClassifyData(apiKey),
                new SubscriberCallBack<>(new ApiCallback<CookBookClassifyModel>() {
                    @Override
                    public void onSuccess(CookBookClassifyModel model) {
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
