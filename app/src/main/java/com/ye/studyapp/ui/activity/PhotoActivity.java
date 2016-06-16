package com.ye.studyapp.ui.activity;

import android.view.View;

import com.ye.studyapp.R;
import com.ye.studyapp.adapter.TouchImageAdapter;
import com.ye.studyapp.base.BaseTitleBarActivity;
import com.ye.studyapp.ui.widget.ExtendedViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class PhotoActivity extends BaseTitleBarActivity {

    @Bind(R.id.view_pager)
    ExtendedViewPager mViewPager;
    private List<String> mList=new ArrayList<>();

    @Override
    protected View getMainView() {
        View view=View.inflate(context,R.layout.activity_photo,null);
        ivLeft.setImageResource(R.mipmap.back);
        return view;
    }

    @Override
    protected void init() {
        mList.add(getIntent().getStringExtra("url"));
        mViewPager.setAdapter(new TouchImageAdapter(mList));

        setListener();
    }

    private void setListener() {
        //返回
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
