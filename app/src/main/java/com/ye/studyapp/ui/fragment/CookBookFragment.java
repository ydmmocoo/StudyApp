package com.ye.studyapp.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ye.studyapp.R;
import com.ye.studyapp.adapter.CookBookClassifyAdapter;
import com.ye.studyapp.base.MvpFragment;
import com.ye.studyapp.model.CookBookClassifyModel;
import com.ye.studyapp.presenter.CookBookPresenter;
import com.ye.studyapp.retrofit.ApiStores;
import com.ye.studyapp.ui.activity.CookBookListActivity;
import com.ye.studyapp.ui.iview.CookBookView;

import butterknife.Bind;

/**
 * Created by admin on 2016/6/14.
 */

public class CookBookFragment extends MvpFragment<CookBookPresenter> implements CookBookView {

    @Bind(R.id.gv_classify)
    GridView mGridView;
    private CookBookClassifyAdapter mAdapter;
    private CookBookClassifyModel mData;

    @Override
    protected View getMainView() {
        View view=View.inflate(context, R.layout.fragment_cook_book,null);
        return view;
    }

    @Override
    protected void init() {
        mvpPresenter.loadData(ApiStores.NEWS_API_KEY);
        mGridView.setOnItemClickListener(new ClassifyItemClickListener());
    }

    @Override
    protected CookBookPresenter createPresenter() {
        return new CookBookPresenter(this);
    }

    @Override
    public void getDataSuccess(CookBookClassifyModel model) {
        if (model.isStatus()){
            mData=model;
            mAdapter=new CookBookClassifyAdapter(context,mData);
            mGridView.setAdapter(mAdapter);
        }
    }

    @Override
    public void getDataFail(String msg) {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    public class ClassifyItemClickListener implements GridView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(context,CookBookListActivity.class);
            intent.putExtra("id",mData.getTngou().get(position).getId());
            intent.putExtra("name",mData.getTngou().get(position).getName());
            startActivity(intent);
        }
    }
}
