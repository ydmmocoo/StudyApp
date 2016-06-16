package com.ye.studyapp.ui.iview;

import com.ye.studyapp.model.CookBookListModel;

/**
 * Created by admin on 2016/6/14.
 */

public interface CookBookListView {
    void getDataSuccess(CookBookListModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
