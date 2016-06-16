package com.ye.studyapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ye.studyapp.R;
import com.ye.studyapp.model.CookBookClassifyModel;

/**
 * Created by admin on 2016/6/16.
 */

public class CookBookClassifyAdapter extends BaseAdapter{

    private Context mContext;
    private CookBookClassifyModel mData;

    public CookBookClassifyAdapter(Context context, CookBookClassifyModel data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.getTngou().size();
    }

    @Override
    public Object getItem(int position) {
        return mData.getTngou().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=View.inflate(mContext, R.layout.layout_cook_book_classify_item,null);
            holder=new ViewHolder();
            holder.tvClassifyName= (TextView) convertView.findViewById(R.id.tv_classify_name);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.tvClassifyName.setText(mData.getTngou().get(position).getName());
        return convertView;
    }

    class ViewHolder{
        TextView tvClassifyName;
    }
}
