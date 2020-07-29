package com.gxuwz.beethoven.adapter.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.page.index.myview.songlist.SongListActivity;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.WaterFallViewHolder>{
    private Context context;
    List<SongList> songLists;
    SysUser sysUser = null;
    Bitmap usernameUrilB;
    Bitmap songListUrilB[];

    public WaterFallAdapter(Context context,List<SongList> songLists,SysUser sysUser,Bitmap usernameUrilB) {
        this.context = context;
        this.songLists = songLists;
        this.sysUser = sysUser;
        this.usernameUrilB = usernameUrilB;
        this.songListUrilB = new Bitmap[songLists.size()];
    }
    @NonNull
    @Override
    public WaterFallAdapter.WaterFallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WaterFallViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_water_fall_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WaterFallAdapter.WaterFallViewHolder holder, int position) {
        SongList songList = songLists.get(position);
        final Handler perPicViewHandle = new Handler() {
            public void handleMessage(android.os.Message msg) {
                songListUrilB[position] = (Bitmap) msg.obj;
                holder.iv.setImageBitmap(MergeImage.rounded(songListUrilB[position],5));
            };
        };
        new Thread(){
            @Override
            public void run() {
                Bitmap imageDate = HttpUtil.getImage(songList.getSongListUrl());
                Message msg = new Message();
                msg.obj = imageDate;
                perPicViewHandle.sendMessage(msg);
            }
        }.start();
        holder.songListName.setText(songList.getSongListName());
        holder.songListNumber.setText(""+songList.getSongNumber().intValue()+"首");
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, SongListActivity.class);
                SongList songList = songLists.get(position);
                songList.setSongListMusic(songList.get_links().getSongListMusics().getHref()+"");
                Bundle b = new Bundle();
                b.putSerializable("songList", songList);
                b.putSerializable("sysUser",sysUser);
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songLists.size();
    }

    class WaterFallViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView songListName;
        private TextView songListNumber;
        public WaterFallViewHolder(@NonNull View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.song_list_url);
            songListName = itemView.findViewById(R.id.song_list_name);
            songListNumber = itemView.findViewById(R.id.song_list_number);
        }
    }
}