package com.ye.studyapp.ui.iview;

import com.ye.studyapp.model.PhotoModel;

/**
 * Created by admin on 2016/6/14.
 */

public interface PhotoView {
    void getDataSuccess(PhotoModel model);
    void getDataFail(String msg);
    void showLoading();
    void hideLoading();
}
