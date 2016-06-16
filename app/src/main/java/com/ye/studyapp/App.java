package com.ye.studyapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.socks.library.KLog;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by YE on 2016/4/19.
 */
public class App extends Application {

    private static List<Activity> activitys = null;
    public static String cacheDir = "";
    public static Context mAppContext = null;

    @Override
    public void onCreate() {
        super.onCreate();

        init();

        //初始化KLog
        KLog.init(BuildConfig.LOG_DEBUG);
        // fresco图片库的初始化
        ImagePipelineConfig.Builder configBuilder = ImagePipelineConfig.newBuilder(this);
        ImagePipelineConfig imagePipelineConfig = configBuilder.build();
        Fresco.initialize(this, imagePipelineConfig);

        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null && ExistSDCard()) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    private void init() {
        activitys = new LinkedList<Activity>();
    }


    public static void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        } else {
            activitys.add(activity);
        }

    }

    /**
     * 退出并finish所有activity
     */
    public static void exit() {
        finishAll();
        System.exit(0);
    }

    public static void finishAll() {
        if (activitys != null && activitys.size() > 0) {
            for (Activity activity : activitys) {
                activity.finish();
            }
        }
    }

    /**
     * 判断SDCard是否存在
     * @return
     */
    private boolean ExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static Context getmAppContext() {
        return mAppContext;
    }
}
