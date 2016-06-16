package com.ye.studyapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ye.studyapp.R;
import com.ye.studyapp.model.PhotoModel;

/**
 * Created by admin on 2016/6/15.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private Context context;
    private PhotoModel mPhotoModel;
    private OnItemClickLitener mOnItemClickLitener;

    public PhotoAdapter(Context context, PhotoModel data) {
        this.context=context;
        this.mPhotoModel = data;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    public void setData(PhotoModel data){
        this.mPhotoModel=data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.layout_photo_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        PhotoModel.TngouEntity tngouEntity = mPhotoModel.getTngou().get(position);
        viewHolder.ivPhoto.setImageURI(Uri.parse("http://tnfs.tngou.net/image"+tngouEntity.getImg()));

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
        return mPhotoModel.getTngou().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView ivPhoto;

        public ViewHolder(View view) {
            super(view);
            ivPhoto= (SimpleDraweeView) view.findViewById(R.id.sd_img);
        }
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
