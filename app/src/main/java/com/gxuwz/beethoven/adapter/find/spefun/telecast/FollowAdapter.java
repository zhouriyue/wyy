package com.gxuwz.beethoven.adapter.find.spefun.telecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.find.Telecast;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.FollowViewHolder> {
    Context context;
    List<Telecast> telecastList;

    public FollowAdapter(Context context, List<Telecast> telecastList) {
        this.context = context;
        this.telecastList = telecastList;
    }

    @NonNull
    @Override
    public FollowAdapter.FollowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FollowAdapter.FollowViewHolder(LayoutInflater.from(context).inflate(R.layout.stc_follow_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull FollowAdapter.FollowViewHolder holder, int position) {
        Telecast telecast = telecastList.get(position);
        SysUser sysUser = telecast.getSysUser();
        holder.picPer.setImageBitmap(MergeImage.circleShow(HttpUtils.getRes(telecast.getImg(),context)));
        holder.title.setText(telecast.getTitle());
        holder.detail.setText(telecast.getDetail());
    }

    @Override
    public int getItemCount() {
        return telecastList.size();
    }

    class FollowViewHolder extends RecyclerView.ViewHolder{

        ImageView picPer;
        TextView title,detail;

        public FollowViewHolder(@NonNull View itemView) {
            super(itemView);
            picPer = itemView.findViewById(R.id.pic_per);
            title = itemView.findViewById(R.id.title);
            detail = itemView.findViewById(R.id.detail);
        }
    }
}
