package com.ye.studyapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ye.studyapp.R;

import butterknife.ButterKnife;

/**
 * Created by YE on 2016/4/19.
 */
public abstract class BaseFragment extends Fragment {

    public Context context;
    private View view;
    public FrameLayout flContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context=getActivity();
        view=View.inflate(context, R.layout.base_fragment,null);

        initFrame();

        return view;
    }

    private void initFrame() {
        flContent = (FrameLayout) view.findViewById(R.id.fl_root);

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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
