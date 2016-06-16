package com.ye.studyapp.ui.activity;

import android.net.Uri;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.socks.library.KLog;
import com.ye.studyapp.R;
import com.ye.studyapp.base.MvpActivity;
import com.ye.studyapp.model.CookBookDetailModel;
import com.ye.studyapp.presenter.CookBookDetailPresenter;
import com.ye.studyapp.retrofit.ApiStores;
import com.ye.studyapp.ui.iview.CookBookDetailView;

import butterknife.Bind;

public class CookBookDetailActivity extends MvpActivity<CookBookDetailPresenter> implements CookBookDetailView {

    @Bind(R.id.sd_img)
    SimpleDraweeView mSdImg;
    @Bind(R.id.tv_food)
    TextView mTvFood;
    @Bind(R.id.tv_description)
    TextView mTvDescription;
    @Bind(R.id.tv_step)
    TextView mTvStep;
    @Bind(R.id.tv_detail)
    TextView mTvDetail;
    private int id;

    @Override
    protected View getMainView() {
        View view = View.inflate(context, R.layout.activity_cook_book_detail, null);
        ivLeft.setImageResource(R.mipmap.back);
        return view;
    }

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        tvTitle.setText(getIntent().getStringExtra("name"));
        //返回
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mvpPresenter.loadData(ApiStores.NEWS_API_KEY, id);
    }

    @Override
    protected CookBookDetailPresenter createPresenter() {
        return new CookBookDetailPresenter(this);
    }

    @Override
    public void getDataSuccess(CookBookDetailModel model) {
        mSdImg.setImageURI(Uri.parse("http://tnfs.tngou.net/image"+model.getImg()));
        mTvFood.setText("所需材料："+model.getFood());
        mTvDescription.setText("准备工作:"+model.getDescription());
        mTvDetail.setText(Html.fromHtml(model.getMessage()));
        mTvStep.setText("步骤:"+model.getKeywords());
    }

    @Override
    public void getDataFail(String msg) {
        KLog.e(msg);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }
}
