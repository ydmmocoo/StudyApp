package com.ye.studyapp.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.socks.library.KLog;
import com.ye.studyapp.R;
import com.ye.studyapp.adapter.CookBookListAdapter;
import com.ye.studyapp.base.MvpActivity;
import com.ye.studyapp.model.CookBookListModel;
import com.ye.studyapp.presenter.CookBookListPresenter;
import com.ye.studyapp.retrofit.ApiStores;
import com.ye.studyapp.ui.iview.CookBookListView;

import butterknife.Bind;

public class CookBookListActivity extends MvpActivity<CookBookListPresenter> implements CookBookListView,XRecyclerView.LoadingListener{

    @Bind(R.id.content_view)
    XRecyclerView mXRecyclerView;
    private CookBookListAdapter mAdapter;
    private CookBookListModel mData;
    private int id,page=1;
    private boolean isFirst=true;

    @Override
    protected View getMainView() {
        View view=View.inflate(context, R.layout.activity_cook_book_list,null);
        ivLeft.setImageResource(R.mipmap.back);
        return view;
    }

    @Override
    protected void initData() {
        id=getIntent().getIntExtra("id",0);
        tvTitle.setText(getIntent().getStringExtra("name"));
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mXRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mXRecyclerView.setLoadingListener(this);

        mvpPresenter.loadData(ApiStores.NEWS_API_KEY,id,page);
    }

    @Override
    protected CookBookListPresenter createPresenter() {
        return new CookBookListPresenter(this);
    }

    @Override
    public void getDataSuccess(CookBookListModel model) {
        KLog.e(new Gson().toJson(model));
        if (model.getTngou()!=null){
            if (isFirst){
                isFirst=false;
                mData=model;
                mAdapter=new CookBookListAdapter(context,mData);
                mXRecyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickLitener(new ItemClickListener());
            }else {
                if (page==1){
                    mData.getTngou().clear();
                    mData=model;
                    mAdapter.setData(mData);
                    mXRecyclerView.refreshComplete();
                }else {
                    mData.getTngou().addAll(model.getTngou());
                    mAdapter.setData(mData);
                    mXRecyclerView.loadMoreComplete();
                }
            }
        }
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

    @Override
    public void onRefresh() {
        page=1;
        mvpPresenter.loadData(ApiStores.NEWS_API_KEY,id,page);
    }

    @Override
    public void onLoadMore() {
        page++;
        mvpPresenter.loadData(ApiStores.NEWS_API_KEY,id,page);
    }

    public class ItemClickListener implements CookBookListAdapter.OnItemClickLitener{
        @Override
        public void onItemClick(View view, int position) {
            Intent intent=new Intent(context,CookBookDetailActivity.class);
            intent.putExtra("id",mData.getTngou().get(position-1).getId());
            intent.putExtra("name",mData.getTngou().get(position-1).getName());
            startActivity(intent);
        }
    }
}
