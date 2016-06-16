package com.ye.studyapp.ui.iview;

import com.ye.studyapp.model.CookBookClassifyModel;

/**
 * Created by admin on 2016/6/14.
 */

public interface CookBookView {
    void getDataSuccess(CookBookClassifyModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
