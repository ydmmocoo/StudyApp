package com.ye.studyapp.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.socks.library.KLog;
import com.ye.studyapp.App;
import com.ye.studyapp.R;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by YE on 2016/4/19.
 */
public abstract class BaseTitleBarActivity extends AppCompatActivity {

    public LinearLayout llRoot;
    public ImageView ivLeft;
    public TextView tvTitle;
    public ImageView ivRight;
    public FrameLayout flContent;
    public Context context;
    public SharedPreferences sp;
    protected Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_title_activity);

        App.addActivity(this);
        SwipeBackHelper.onCreate(this);

        initFrame();
        init();
    }

    private void initFrame() {
        context = this;
        sp=getSharedPreferences("config",MODE_PRIVATE);
        llRoot= (LinearLayout) findViewById(R.id.ll_root);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivLeft = (ImageView) findViewById(R.id.iv_left_img);
        ivRight = (ImageView) findViewById(R.id.iv_right_img);
        flContent = (FrameLayout) findViewById(R.id.fl_activity_content);

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

    protected void init(){
    }

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
