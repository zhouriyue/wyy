package com.gxuwz.beethoven.adapter.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.current.PlayApp;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.List;

public class PlayAppAdapter extends RecyclerView.Adapter<PlayAppAdapter.PlayAppViewHolder> {

    Context context;
    List<PlayApp> playAppList;

    public PlayAppAdapter(Context context, List<PlayApp> playAppList) {
        this.context = context;
        this.playAppList = playAppList;
    }

    @NonNull
    @Override
    public PlayAppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlayAppViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my_songlist,null));
    }

    @Override
    public void onBindViewHolder(@NonNull PlayAppViewHolder holder, int position) {
        PlayApp playApp = playAppList.get(position);
        String url = StaticHttp.STATIC_BASEURL+playApp.getCoverPicture();
        if(playApp.getCoverPicture()!=null) {
            MergeImage.showGlideImgDb(context,url,holder.songListUrl,10);
        } else {
            MergeImage.showGlideImgDb(context,StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,holder.songListUrl,10);
        }
        switch (playApp.getType()) {
            case 1:{
                holder.songListName.setText("上次播放歌曲："+playApp.getName());

            };break;
            case 2:{
                holder.songListName.setText(playApp.getName());
            };break;
        }

        holder.songListNumber.setText(playApp.getNumber()+"");
    }

    @Override
    public int getItemCount() {
        return playAppList.size();
    }

    class PlayAppViewHolder extends RecyclerView.ViewHolder{

        ImageView songListUrl;
        TextView songListName,songListNumber;

        public PlayAppViewHolder(@NonNull View itemView) {
            super(itemView);
            songListUrl = itemView.findViewById(R.id.song_list_url);
            songListName = itemView.findViewById(R.id.song_list_name);
            songListNumber = itemView.findViewById(R.id.song_list_number);
        }
    }
}
