package com.seatrend.environment.inspection.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.seatrend.environment.inspection.R;
import com.seatrend.environment.inspection.ShowPictureActivity;
import com.seatrend.environment.inspection.entity.ShareEntity;

import java.util.ArrayList;

/**
 * Created by ly on 2020/5/18 14:14
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyHolder> {

    private Context mContext;
    private ArrayList<ShareEntity.PhotoListBean> list;

    public PhotoAdapter(Context ctx, ArrayList<ShareEntity.PhotoListBean> data) {
        this.mContext = ctx;
        this.list = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.recycle_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.initItemView(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView iv;
        private TextView tv_zpmc;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            initView();
        }

        private void initView() {
            iv = view.findViewById(R.id.ry_iv_item);
            tv_zpmc = view.findViewById(R.id.tv_zpmc);
            bindEvent();
        }

        public void initItemView(ShareEntity.PhotoListBean s) {
            Glide.with(mContext).load(s.getZplj()).centerCrop().into(iv);
            tv_zpmc.setText(s.getZpmc());
        }

        private void bindEvent() {

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.setClass(mContext, ShowPictureActivity.class);
                    intent.putExtra("zpmc",list.get(getAdapterPosition()).getZpmc());
                    intent.putExtra("zplj",list.get(getAdapterPosition()).getZplj());
                    mContext.startActivity(intent);
                }
            });
        }
    }

}
