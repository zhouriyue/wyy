package com.gxuwz.beethoven.adapter.find.spefun.slplaza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {

    Context context;
    List<SongList> songLists;
    int itemWidth;

    public RecommendAdapter(Context context, List<SongList> songLists) {
        this.context = context;
        this.songLists = songLists;
        itemWidth = (int) ((WindowPixels.WIDTH-60)/3);
    }

    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendAdapter.RecommendViewHolder(LayoutInflater.from(context).inflate(R.layout.slp_recommend_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendAdapter.RecommendViewHolder holder, int position) {
        SongList songList = songLists.get(position);
        holder.slImg.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(songList.getSongListUrl(),context),itemWidth,itemWidth,4));
        holder.playNumber.setText(songList.getPlayNumber()+"");
        holder.slName.setText(songList.getSongListName());
    }

    @Override
    public int getItemCount() {
        return songLists.size();
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder{

        ImageView slImg;
        TextView playNumber,slName;

        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            slImg = itemView.findViewById(R.id.sl_img);
            playNumber = itemView.findViewById(R.id.play_number);
            slName = itemView.findViewById(R.id.sl_name);
        }
    }
}
