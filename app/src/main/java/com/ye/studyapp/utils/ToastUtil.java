package com.ye.studyapp.utils;

import android.widget.Toast;

import com.ye.studyapp.App;


/**
 * Created by HugoXie on 16/5/23.
 */
public class ToastUtil {

    public static void showShort(String msg) {
        Toast.makeText(App.getmAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg) {
        Toast.makeText(App.getmAppContext(), msg, Toast.LENGTH_LONG).show();
    }
}
