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

public class AnchorAdapter extends RecyclerView.Adapter<AnchorAdapter.AnchorViewHolder> {

    Context context;
    List<Anchor> anchorList;

    public AnchorAdapter(Context context, List<Anchor> anchorList) {
        this.context = context;
        this.anchorList = anchorList;
    }

    @NonNull
    @Override
    public AnchorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnchorViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_my_collect_anchor_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull AnchorViewHolder holder, int position) {
        Anchor anchor = anchorList.get(position);
        holder.picPer.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(anchor.getPicPer(),context)));
        holder.title.setText(anchor.getTitle());
        holder.content.setText(anchor.getContent());
    }

    @Override
    public int getItemCount() {
        return anchorList.size();
    }

    class AnchorViewHolder extends RecyclerView.ViewHolder{

        ImageView picPer;
        TextView title,content;

        public AnchorViewHolder(@NonNull View itemView) {
            super(itemView);
            picPer = itemView.findViewById(R.id.pic_per);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }
}
