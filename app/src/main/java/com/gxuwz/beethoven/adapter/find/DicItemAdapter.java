package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.Dic;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class DicItemAdapter extends RecyclerView.Adapter<DicItemAdapter.DicItemViewHolder> {

    Context context;
    List<Dic> dicList;

    public DicItemAdapter(Context context, List<Dic> dicList) {
        this.context = context;
        this.dicList = dicList;
    }

    @NonNull
    @Override
    public DicItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DicItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dic_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull DicItemViewHolder holder, int position) {
        Dic dic = dicList.get(position);
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(dic.getDiagonal(),context),48,48,5));
        holder.detail.setText(dic.getDetail());
        holder.singerName.setText(dic.getSinger().getSingerName());
        holder.songName.setText(dic.getName());
    }

    @Override
    public int getItemCount() {
        return dicList.size();
    }

    class DicItemViewHolder extends RecyclerView.ViewHolder{

        ImageView diagonal;
        TextView songName,singerName,detail;

        public DicItemViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            songName = itemView.findViewById(R.id.song_name);
            singerName = itemView.findViewById(R.id.singer_name);
            detail = itemView.findViewById(R.id.detail);
        }
    }
}
