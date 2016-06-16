package com.ye.studyapp.ui.iview;

import com.ye.studyapp.model.HomeModel;

/**
 * Created by admin on 2016/6/14.
 */

public interface HomeView {
    void getDataSuccess(HomeModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
