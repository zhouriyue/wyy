package com.gxuwz.beethoven.adapter.cloud;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.dynamics.Dynamics;
import com.gxuwz.beethoven.model.entity.dynamics.ImageWordDynamics;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class DynamicsAdapter extends RecyclerView.Adapter<DynamicsAdapter.DynamicsViewHolder> {

    Context context;
    List<Dynamics> dynamicsList;

    public DynamicsAdapter(Context context, List<Dynamics> dynamicsList) {
        this.context = context;
        this.dynamicsList = dynamicsList;
    }

    @NonNull
    @Override
    public DynamicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DynamicsViewHolder(LayoutInflater.from(context).inflate(R.layout.dynamics_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicsViewHolder holder, int position) {
        if (dynamicsList.get(position).getType().equals("ImageWordDynamics")) {
            ImageWordDynamics imageWordDynamics = (ImageWordDynamics) dynamicsList.get(position);
            MergeImage.showGlideImgDb(context,R.drawable.youth,holder.dynPerPic,24);
            holder.username.setText("周日月");
            holder.dyn_time.setText("19:20");
            holder.dyn_type.setText("分享歌单");
            MergeImage.showGlideImgDb(context,R.drawable.zhoushen,holder.diagonal,1);
            String content = imageWordDynamics.getFriend().toString()+imageWordDynamics.getContent().toString();
            SpannableString spannableString = new SpannableString(content);
            //设置文字颜色
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#4e7cb0"));
            spannableString.setSpan(foregroundColorSpan, 0, content.lastIndexOf("#")+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            holder.content.setText(spannableString);
        }

    }

    @Override
    public int getItemCount() {
        return dynamicsList.size();
    }

    class DynamicsViewHolder extends RecyclerView.ViewHolder{
        ImageView dynPerPic,diagonal;
        TextView username,dyn_type,dyn_time,content;
        public DynamicsViewHolder(@NonNull View itemView) {
            super(itemView);
            dynPerPic = itemView.findViewById(R.id.dyn_per_pic);
            diagonal = itemView.findViewById(R.id.diagonal);
            username = itemView.findViewById(R.id.username);
            dyn_type = itemView.findViewById(R.id.dyn_type);
            dyn_time = itemView.findViewById(R.id.dyn_time);
            content = itemView.findViewById(R.id.content);
        }
    }
}
