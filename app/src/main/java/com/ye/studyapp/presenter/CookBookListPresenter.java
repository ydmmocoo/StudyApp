package com.ye.studyapp.presenter;

import com.ye.studyapp.base.BasePresenter;
import com.ye.studyapp.model.CookBookListModel;
import com.ye.studyapp.rxjava.ApiCallback;
import com.ye.studyapp.rxjava.SubscriberCallBack;
import com.ye.studyapp.ui.iview.CookBookListView;

/**
 * Created by admin on 2016/6/16.
 */

public class CookBookListPresenter extends BasePresenter<CookBookListView> {

    public CookBookListPresenter(CookBookListView view) {
        attachView(view);
    }


    public void loadData(String apiKey,int id,int page) {
        mvpView.showLoading();
        addSubscription(apiStores.loadListData(apiKey,id,page),
                new SubscriberCallBack<>(new ApiCallback<CookBookListModel>() {
                    @Override
                    public void onSuccess(CookBookListModel model) {
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
