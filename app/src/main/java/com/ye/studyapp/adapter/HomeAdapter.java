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
import com.ye.studyapp.model.HomeModel;

/**
 * Created by admin on 2016/6/15.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private HomeModel mHomeModel;
    private OnItemClickLitener mOnItemClickLitener;

    public HomeAdapter(Context context,HomeModel data) {
        this.context=context;
        this.mHomeModel = data;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    public void setData(HomeModel data){
        this.mHomeModel=data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.layout_home_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        HomeModel.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity contentlistEntity
                = mHomeModel.getShowapi_res_body().getPagebean().getContentlist().get(position);
        if (contentlistEntity.getImageurls().size()!=0) {
            viewHolder.ivPhoto.setImageURI(Uri.parse(contentlistEntity.getImageurls().get(0).getUrl()));
        }
        viewHolder.tvTitle.setText(contentlistEntity.getTitle());
        viewHolder.tvContent.setText(contentlistEntity.getDesc());

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
        return mHomeModel.getShowapi_res_body().getPagebean().getContentlist().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView ivPhoto;
        TextView tvTitle;
        TextView tvContent;

        public ViewHolder(View view) {
            super(view);
            ivPhoto= (SimpleDraweeView) view.findViewById(R.id.sd_news_img);
            tvTitle= (TextView) view.findViewById(R.id.tv_title);
            tvContent= (TextView) view.findViewById(R.id.tv_content);
        }
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
