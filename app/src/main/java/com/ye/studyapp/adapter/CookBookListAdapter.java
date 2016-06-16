package com.ye.studyapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ye.studyapp.R;
import com.ye.studyapp.model.CookBookListModel;

/**
 * Created by admin on 2016/6/15.
 */

public class CookBookListAdapter extends RecyclerView.Adapter<CookBookListAdapter.ViewHolder> {

    private Context context;
    private CookBookListModel mData;
    private OnItemClickLitener mOnItemClickLitener;

    public CookBookListAdapter(Context context, CookBookListModel data) {
        this.context=context;
        this.mData = data;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    public void setData(CookBookListModel data){
        this.mData=data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.layout_cook_book_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        CookBookListModel.TngouEntity tngouEntity = mData.getTngou().get(position);
        viewHolder.ivPhoto.setImageURI(Uri.parse("http://tnfs.tngou.net/image"+tngouEntity.getImg()));
        viewHolder.tvName.setText(tngouEntity.getName());
        viewHolder.tvFood.setText("所需材料:"+tngouEntity.getFood());
        viewHolder.tvDescription.setText("简介："+tngouEntity.getDescription());

        if (mOnItemClickLitener != null)
        {
            viewHolder.itemView.setBackgroundResource(R.drawable.recycler_bg);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.getTngou().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView ivPhoto;
        TextView tvName;
        TextView tvFood;
        TextView tvDescription;

        public ViewHolder(View view) {
            super(view);
            ivPhoto= (SimpleDraweeView) view.findViewById(R.id.sd_img);
            tvName= (TextView) view.findViewById(R.id.tv_name);
            tvFood= (TextView) view.findViewById(R.id.tv_food);
            tvDescription= (TextView) view.findViewById(R.id.tv_description);
        }
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
