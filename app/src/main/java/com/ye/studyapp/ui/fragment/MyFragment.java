package com.ye.studyapp.ui.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ye.studyapp.base.BaseFragment;

/**
 * Created by admin on 2016/6/14.
 */

public class MyFragment extends BaseFragment{

    @Override
    protected View getMainView() {
        TextView view=new TextView(context);
        view.setGravity(Gravity.CENTER);
        view.setText("我的");
        return view;
    }
}
