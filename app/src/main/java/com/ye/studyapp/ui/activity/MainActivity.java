package com.ye.studyapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.ye.studyapp.App;
import com.ye.studyapp.R;
import com.ye.studyapp.adapter.TabEntity;
import com.ye.studyapp.ui.fragment.CookBookFragment;
import com.ye.studyapp.ui.fragment.HomeFragment;
import com.ye.studyapp.ui.fragment.MyFragment;
import com.ye.studyapp.ui.fragment.PhotoFragment;
import com.ye.studyapp.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tab)
    CommonTabLayout tab;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles;
    private int[] iconUnselectIds = {
            R.mipmap.home, R.mipmap.order, R.mipmap.office, R.mipmap.personalcenter};
    private int[] iconSelectIds = {
            R.mipmap.home_check, R.mipmap.order_check, R.mipmap.office_check, R.mipmap.personalcenter_check};
    private ArrayList<CustomTabEntity> tabs = new ArrayList<CustomTabEntity>();
    private long mPressedTime = 0;
    private HomeFragment mHomeFragment;
    private PhotoFragment mPhotoFragment;
    private CookBookFragment mCookBookFragment;
    private MyFragment mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        App.addActivity(this);

        initData();
        setListener();
    }

    private void initData() {
        titles=getResources().getStringArray(R.array.main_tab);
        for (int i = 0; i < titles.length; i++) {
            tabs.add(new TabEntity(titles[i],iconSelectIds[i],iconUnselectIds[i]));
        }
        mHomeFragment=new HomeFragment();
        mPhotoFragment=new PhotoFragment();
        mCookBookFragment=new CookBookFragment();
        mMyFragment=new MyFragment();
        fragments.add(mHomeFragment);
        fragments.add(mPhotoFragment);
        fragments.add(mCookBookFragment);
        fragments.add(mMyFragment);

        tab.setTabData(tabs, this, R.id.fl_content, fragments);
    }

    private void setListener() {
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        mHomeFragment.onRefresh();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            ToastUtil.showShort(getResources().getString(R.string.press_again));
            mPressedTime = mNowTime;
        } else {//退出程序
            App.exit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
