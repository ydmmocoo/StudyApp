package com.ye.studyapp.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.socks.library.KLog;
import com.ye.studyapp.App;
import com.ye.studyapp.R;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by YE on 2016/4/19.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public FrameLayout flContent;
    public Context context;
    public SharedPreferences sp;
    protected Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);

        App.addActivity(this);
        SwipeBackHelper.onCreate(this);

        initFrame();
    }

    private void initFrame() {
        context = this;
        sp=getSharedPreferences("config",MODE_PRIVATE);
        flContent = (FrameLayout) findViewById(R.id.fl_root);

        // 主体界面
        View mainView = getMainView();
        if (mainView != null) {
            ButterKnife.bind(this, mainView);
            flContent.addView(mainView);
        }
    }

    /**
     * 主体界面，继承者自己实现
     */
    protected abstract View getMainView();

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        KLog.i("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        KLog.i("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        KLog.i("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        KLog.i("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        KLog.i("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        SwipeBackHelper.onDestroy(this);
        unsubscribe();
        KLog.i("onDestroy");
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
