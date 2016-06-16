package com.ye.studyapp.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.ye.studyapp.R;
import com.ye.studyapp.adapter.PhotoAdapter;
import com.ye.studyapp.base.MvpFragment;
import com.ye.studyapp.model.PhotoModel;
import com.ye.studyapp.presenter.PhotoPresenter;
import com.ye.studyapp.retrofit.ApiStores;
import com.ye.studyapp.ui.activity.PhotoActivity;
import com.ye.studyapp.ui.iview.PhotoView;
import com.ye.studyapp.utils.ACache;

import butterknife.Bind;

/**
 * Created by admin on 2016/6/14.
 */

public class PhotoFragment extends MvpFragment<PhotoPresenter> implements PhotoView, XRecyclerView.LoadingListener {

    @Bind(R.id.content_view)
    XRecyclerView mXRecyclerView;
    @Bind(R.id.ll_root)
    LinearLayout mLlRoot;
    private View mErrorView;
    private View mNoNetWorkView;
    private View mEmptyView;
    //private View mLoadingView;
    private PhotoAdapter mAdapter;
    private PhotoModel mPhotoModel;
    private boolean isFirst = true;
    private int page = 1;
    private ACache mACache;

    @Override
    protected View getMainView() {
        View view = View.inflate(context, R.layout.fragment_home, null);
        return view;
    }

    @Override
    protected PhotoPresenter createPresenter() {
        return new PhotoPresenter(this);
    }

    @Override
    protected void init() {
        mACache=ACache.get(context);
        mErrorView = View.inflate(context, R.layout.error_view, null);
        mNoNetWorkView = View.inflate(context, R.layout.no_network_view, null);
        mEmptyView = View.inflate(context, R.layout.empty_view, null);
        //mLoadingView=View.inflate(context,R.layout.loading_view,null);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mXRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mXRecyclerView.setLoadingListener(this);

        mvpPresenter.loadData(ApiStores.NEWS_API_KEY, page);
    }

    @Override
    public void getDataSuccess(PhotoModel model) {
        if (model.getTngou()!= null) {
            if (model.getTotal() != 0) {
                mLlRoot.removeAllViews();
                mLlRoot.addView(mXRecyclerView);
                if (isFirst) {
                    isFirst = false;
                    mPhotoModel = model;
                    mAdapter = new PhotoAdapter(context, mPhotoModel);
                    mXRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickLitener(new ItemClickListener());
                } else {
                    if (page == 1) {
                        mPhotoModel.getTngou().clear();
                        mPhotoModel = model;
                        mAdapter.setData(mPhotoModel);
                        mXRecyclerView.refreshComplete();
                    } else {
                        mPhotoModel.getTngou().addAll(model.getTngou());
                        mAdapter.setData(mPhotoModel);
                        mXRecyclerView.loadMoreComplete();
                    }
                }
            } else {
                mLlRoot.removeAllViews();
                mLlRoot.addView(mEmptyView);
            }
        } else {
            mLlRoot.removeAllViews();
            mLlRoot.addView(mErrorView);
        }
    }

    @Override
    public void getDataFail(String msg) {
        mLlRoot.removeAllViews();
        mLlRoot.addView(mNoNetWorkView);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onRefresh() {
        page = 1;
        mvpPresenter.loadData(ApiStores.NEWS_API_KEY, page);
    }

    @Override
    public void onLoadMore() {
        page++;
        mvpPresenter.loadData(ApiStores.NEWS_API_KEY, page);
    }

    public class ItemClickListener implements PhotoAdapter.OnItemClickLitener{
        @Override
        public void onItemClick(View view, int position) {
            Intent intent=new Intent(context, PhotoActivity.class);
            intent.putExtra("url","http://tnfs.tngou.net/image"+mPhotoModel.getTngou().get(position-1).getImg());
            startActivity(intent);
        }
    }
}
