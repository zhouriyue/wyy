package com.gxuwz.beethoven.adapter.my.localmusic.singer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.my.local.singer.SingerItem;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class SingerItemAdapter extends RecyclerView.Adapter<SingerItemAdapter.SingerViewHolder> {

    Context context;
    List<SingerItem> singerItemList;

    public SingerItemAdapter(Context context, List<SingerItem> singerItemList) {
        this.context = context;
        this.singerItemList = singerItemList;
    }

    @NonNull
    @Override
    public SingerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SingerViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_my_local_singer_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SingerViewHolder holder, int position) {
        SingerItem singerItem = singerItemList.get(position);
        holder.picPer.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(singerItem.getPerPic(),context)));
        holder.singerName.setText(singerItem.getSingerName());
        holder.count.setText(singerItem.getCount()+"首");
    }

    @Override
    public int getItemCount() {
        return singerItemList.size();
    }

    class SingerViewHolder extends RecyclerView.ViewHolder{

        ImageView picPer;
        TextView singerName,count;

        public SingerViewHolder(@NonNull View itemView) {
            super(itemView);
            picPer = itemView.findViewById(R.id.pic_per);
            singerName = itemView.findViewById(R.id.singer_name);
            count = itemView.findViewById(R.id.count);
        }
    }
}
