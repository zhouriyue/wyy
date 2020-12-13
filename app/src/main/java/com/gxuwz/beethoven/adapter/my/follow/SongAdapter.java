package com.gxuwz.beethoven.adapter.my.follow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.my.follow.Follow;
import com.gxuwz.beethoven.model.entity.my.follow.Singer;
import com.gxuwz.beethoven.model.entity.my.follow.Song;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViwHolder> {

    Context context;
    List<Follow> followList;

    public SongAdapter(Context context, List<Follow> followList) {
        this.context = context;
        this.followList = followList;
    }

    @NonNull
    @Override
    public SongViwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongViwHolder(LayoutInflater.from(context).inflate(R.layout.follow_song_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SongViwHolder holder, int position) {
        Follow follow = followList.get(position);
        Singer singer = follow.getSinger();
        List<Song> songList = follow.getSong();
        holder.singerPerPic.setImageBitmap(MergeImage.circleShow(HttpUtils.getRes(singer.getPerPic(),context)));
        holder.singerName.setText(singer.getSingerName());
        holder.content.setText(follow.getDetail());

        holder.listRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        holder.listRv.setAdapter(new SongListAdapter(context,songList));
    }

    @Override
    public int getItemCount() {
        return followList.size();
    }

    class SongViwHolder extends RecyclerView.ViewHolder{

        ImageView singerPerPic;
        TextView singerName,content;
        RecyclerView listRv;

        public SongViwHolder(@NonNull View itemView) {
            super(itemView);
            singerPerPic = itemView.findViewById(R.id.singer_per_pic);
            singerName = itemView.findViewById(R.id.singer_name);
            content = itemView.findViewById(R.id.content);
            listRv = itemView.findViewById(R.id.list_rv);
        }
    }
}
