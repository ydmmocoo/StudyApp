package com.ye.studyapp.ui.iview;

import com.ye.studyapp.model.CookBookDetailModel;

/**
 * Created by admin on 2016/6/14.
 */

public interface CookBookDetailView {
    void getDataSuccess(CookBookDetailModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
