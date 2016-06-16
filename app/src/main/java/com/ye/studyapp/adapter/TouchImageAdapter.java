package com.ye.studyapp.adapter;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ye.studyapp.ui.widget.TouchImageView;

import java.util.List;

/**
 * Created by admin on 2016/6/15.
 */

public class TouchImageAdapter extends PagerAdapter {

    private List<String> mImages;

    public TouchImageAdapter(List<String> images) {
        this.mImages=images;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        TouchImageView img = new TouchImageView(container.getContext());
        img.setImageURI(Uri.parse(mImages.get(position)));
        container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}