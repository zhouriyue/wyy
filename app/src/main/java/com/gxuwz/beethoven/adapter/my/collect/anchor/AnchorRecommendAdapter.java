package com.gxuwz.beethoven.adapter.my.collect.anchor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.my.local.anchor.Anchor;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class AnchorRecommendAdapter extends RecyclerView.Adapter<AnchorRecommendAdapter.AnchorRecommendViewHolder> {

    Context context;
    List<Anchor> anchorList;

    public AnchorRecommendAdapter(Context context, List<Anchor> anchorList) {
        this.context = context;
        this.anchorList = anchorList;
    }

    @NonNull
    @Override
    public AnchorRecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnchorRecommendViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_my_collect_anchor_recommend_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull AnchorRecommendViewHolder holder, int position) {
        Anchor anchor = anchorList.get(position);
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(anchor.getDiagonal(),context),180,100));
        holder.perPic.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(anchor.getPicPer(),context)));
        holder.anchorType.setText(anchor.getAnchorType());
        holder.title.setText(anchor.getTitle());
        holder.username.setText(anchor.getUsername());
    }

    @Override
    public int getItemCount() {
        return anchorList.size();
    }

    class AnchorRecommendViewHolder extends RecyclerView.ViewHolder{

        ImageView diagonal,perPic;
        TextView anchorType,heat,title,username;

        public AnchorRecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            perPic = itemView.findViewById(R.id.per_pic);
            anchorType = itemView.findViewById(R.id.anchor_type);
            heat = itemView.findViewById(R.id.heat);
            title = itemView.findViewById(R.id.title);
            username = itemView.findViewById(R.id.username);
        }
    }
}
