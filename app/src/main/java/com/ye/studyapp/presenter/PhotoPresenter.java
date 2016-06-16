package com.ye.studyapp.presenter;

import com.ye.studyapp.base.BasePresenter;
import com.ye.studyapp.model.PhotoModel;
import com.ye.studyapp.rxjava.ApiCallback;
import com.ye.studyapp.rxjava.SubscriberCallBack;
import com.ye.studyapp.ui.iview.PhotoView;

/**
 * Created by admin on 2016/6/15.
 */

public class PhotoPresenter extends BasePresenter<PhotoView> {

    public PhotoPresenter(PhotoView view) {
        attachView(view);
    }


    public void loadData(String apiKey,int page) {
        mvpView.showLoading();
        addSubscription(apiStores.loadPhotoData(apiKey,page),
                new SubscriberCallBack<>(new ApiCallback<PhotoModel>() {
                    @Override
                    public void onSuccess(PhotoModel model) {
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
