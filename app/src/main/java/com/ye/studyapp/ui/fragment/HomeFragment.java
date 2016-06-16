package com.ye.studyapp.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.socks.library.KLog;
import com.ye.studyapp.R;
import com.ye.studyapp.adapter.HomeAdapter;
import com.ye.studyapp.base.MvpFragment;
import com.ye.studyapp.model.HomeModel;
import com.ye.studyapp.presenter.HomePresenter;
import com.ye.studyapp.retrofit.ApiStores;
import com.ye.studyapp.ui.activity.NewDetailActivity;
import com.ye.studyapp.ui.iview.HomeView;
import com.ye.studyapp.utils.ACache;

import butterknife.Bind;

/**
 * Created by admin on 2016/6/14.
 */

public class HomeFragment extends MvpFragment<HomePresenter> implements HomeView, XRecyclerView.LoadingListener {

    @Bind(R.id.content_view)
    XRecyclerView mXRecyclerView;
    @Bind(R.id.ll_root)
    LinearLayout mLlRoot;
    private View mErrorView;
    private View mNoNetWorkView;
    private View mEmptyView;
    //private View mLoadingView;
    private HomeAdapter mAdapter;
    private HomeModel mHomeModel;
    private boolean isFirst = true;
    private int page = 1;
    private ACache mACache;

    @Override
    protected View getMainView() {
        View view = View.inflate(context, R.layout.fragment_home, null);
        return view;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void init() {
        mACache=ACache.get(context);
        mErrorView = View.inflate(context, R.layout.error_view, null);
        mNoNetWorkView = View.inflate(context, R.layout.no_network_view, null);
        mEmptyView = View.inflate(context, R.layout.empty_view, null);
        //mLoadingView=View.inflate(context,R.layout.loading_view,null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mXRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mXRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);
        mXRecyclerView.setLoadingListener(this);

        mvpPresenter.loadData(ApiStores.NEWS_API_KEY, page);
    }

    @Override
    public void getDataSuccess(HomeModel model) {
        /*if (mACache!=null){

        }*/
        if (model.getShowapi_res_body() != null) {
            if (model.getShowapi_res_body().getPagebean().getAllNum() != 0) {
                mLlRoot.removeAllViews();
                mLlRoot.addView(mXRecyclerView);
                if (isFirst) {
                    isFirst = false;
                    mHomeModel = model;
                    mAdapter = new HomeAdapter(context, mHomeModel);
                    mXRecyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickLitener(new ItemClickListener());
                    mACache.put("newData",new Gson().toJson(mHomeModel));
                } else {
                    if (page == 1) {
                        mHomeModel.getShowapi_res_body().getPagebean().getContentlist().clear();
                        mHomeModel = model;
                        mAdapter.setData(mHomeModel);
                        mXRecyclerView.refreshComplete();
                    } else {
                        mHomeModel.getShowapi_res_body().getPagebean().getContentlist().addAll(
                                model.getShowapi_res_body().getPagebean().getContentlist());
                        mAdapter.setData(mHomeModel);
                        mXRecyclerView.loadMoreComplete();
                        mACache.put("newData",new Gson().toJson(mHomeModel));
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
        KLog.e(msg);
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

    public class ItemClickListener implements HomeAdapter.OnItemClickLitener{
        @Override
        public void onItemClick(View view, int position) {
            Intent intent=new Intent(context, NewDetailActivity.class);
            intent.putExtra("title",mHomeModel.getShowapi_res_body().getPagebean().getContentlist().get(position-1).getTitle());
            intent.putExtra("link",mHomeModel.getShowapi_res_body().getPagebean().getContentlist().get(position-1).getLink());
            startActivity(intent);
        }
    }
}
