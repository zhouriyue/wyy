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
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class MoreRecommendAdapter extends RecyclerView.Adapter<MoreRecommendAdapter.MoreRecommendViewHolder> {

    Context context;
    List<Telecast> telecastList;

    public MoreRecommendAdapter(Context context, List<Telecast> telecastList) {
        this.context = context;
        this.telecastList = telecastList;
    }

    @NonNull
    @Override
    public MoreRecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoreRecommendViewHolder(LayoutInflater.from(context).inflate(R.layout.stc_morerecommend_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MoreRecommendViewHolder holder, int position) {
        Telecast telecast = telecastList.get(position);
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(telecast.getImg(),context),180,100,6));
        holder.hotNumber.setText(telecast.getHotNumber()+"");
        holder.type.setText(telecast.getType());
        holder.title.setText(telecast.getTitle());
        SysUser sysUser = telecast.getSysUser();
        holder.perPic.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(sysUser.getPerPic(),context)));
        holder.username.setText(sysUser.getUserName());
    }

    @Override
    public int getItemCount() {
        return telecastList.size();
    }

    class MoreRecommendViewHolder extends RecyclerView.ViewHolder{

        ImageView img,perPic;
        TextView title,username,type,hotNumber;

        public MoreRecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            perPic = itemView.findViewById(R.id.per_pic);
            title = itemView.findViewById(R.id.title);
            username = itemView.findViewById(R.id.username);
            type = itemView.findViewById(R.id.type);
            hotNumber =itemView.findViewById(R.id.hot_number);
        }
    }
}
