package com.gxuwz.beethoven.adapter.my;

import android.content.Context;
import android.graphics.Bitmap;
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
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class RecentBroadcastsAdapter extends RecyclerView.Adapter<RecentBroadcastsAdapter.RecentBroadcastsHolder> {

    private Context context;
    List<SongList> songLists;

    public RecentBroadcastsAdapter(Context context,List<SongList> songLists) {
        this.context = context;
        this.songLists = songLists;
    }

    @NonNull
    @Override
    public RecentBroadcastsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecentBroadcastsHolder(LayoutInflater.from(context).inflate(R.layout.activity_water_fall_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecentBroadcastsHolder holder, int position) {
        SongList songList = songLists.get(position);
        final Handler perPicViewHandle = new Handler() {
            public void handleMessage(android.os.Message msg) {
                holder.iv.setImageBitmap(MergeImage.rounded((Bitmap) msg.obj,5));
            };
        };
        new Thread(){
            @Override
            public void run() {
                Bitmap imageDate = HttpUtils.getImage(songList.getSongListUrl());
                Message msg = new Message();
                msg.obj = imageDate;
                perPicViewHandle.sendMessage(msg);
            }
        }.start();
        holder.songListName.setText(songList.getSongListName());
        holder.songListNumber.setText(""+songList.getTag());
    }

    @Override
    public int getItemCount() {
        return songLists.size();
    }

    class RecentBroadcastsHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView songListName;
        private TextView songListNumber;

        public RecentBroadcastsHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.song_list_url);
            songListName = itemView.findViewById(R.id.song_list_name);
            songListNumber = itemView.findViewById(R.id.song_list_number);
        }
    }
}
