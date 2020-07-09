package com.gxuwz.beethoven.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.hanlder.MusicHandler;
import com.gxuwz.beethoven.model.entity.SongListsMusic;
import com.gxuwz.beethoven.util.HttpUtil;

import java.util.List;

public class SongListMusicAdapter extends RecyclerView.Adapter<SongListMusicAdapter.SongListViewHolder> {
    private Context context;
    List<SongListsMusic> songListsMusics;
    private int backItem = -1;
    ImageView ptTagBack;

    public SongListMusicAdapter(Context context, List<SongListsMusic> songListsMusics) {
        this.context = context;
        this.songListsMusics = songListsMusics;
    }

    @NonNull
    @Override
    public SongListMusicAdapter.SongListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongListMusicAdapter.SongListViewHolder(LayoutInflater.from(context).inflate(R.layout.song_list_music_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongListMusicAdapter.SongListViewHolder holder, int position) {
        System.out.println(songListsMusics.get(position).toString());
        SongListsMusic songListsMusic = songListsMusics.get(position);
        System.out.println(holder.position+""+holder.musicName+""+holder.nameAndSinger);
        holder.position.setText((position+1)+"");
        holder.musicName.setText(songListsMusic.getMusicName());
        holder.nameAndSinger.setText(songListsMusic.getSingerName()+"-"+songListsMusic.getMusicName()+".mp3");
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicHandler musicHandler = new MusicHandler();
                musicHandler.setContext(context);
                if(ptTagBack!=null) {
                    ptTagBack.setImageBitmap(HttpUtil.getRes("play3",context));
                }
                holder.ptTag.setBackgroundResource(0);
                holder.ptTag.setImageBitmap(HttpUtil.getRes("stop1",context));
                HttpUtil.get(songListsMusics.get(position).get_links().getMusic().getHref(),musicHandler);
                ptTagBack = holder.ptTag;
            }
        });
    }

    @Override
    public int getItemCount() {
        return songListsMusics.size();
    }

    class SongListViewHolder extends RecyclerView.ViewHolder {

        private TextView position;
        private TextView musicName;
        private TextView nameAndSinger;
        private RelativeLayout relativeLayout;
        private ImageView ptTag;

        public SongListViewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position);
            musicName = itemView.findViewById(R.id.music_name);
            nameAndSinger = itemView.findViewById(R.id.music_singer_name);
            relativeLayout = itemView.findViewById(R.id.id);
            ptTag = itemView.findViewById(R.id.pt_tag);
        }
    }

    public int getBackItem() {
        return backItem;
    }

    public void setBackItem(int backItem) {
        this.backItem = backItem;
    }
}
