package com.gxuwz.beethoven.adapter.find.spefun.rankinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;

import java.util.List;

public class SongItemAdapter extends RecyclerView.Adapter<SongItemAdapter.SongItemViewHolder> {

    Context context;
    List<Song> songList;

    public SongItemAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongItemViewHolder(LayoutInflater.from(context).inflate(R.layout.song_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SongItemViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.songPosition.setText((position+1)+"");
        holder.songName.setText(song.getSongName());
        String singerName = "";
        List<Singer> singerList = song.getSingerList();
        if(singerList.size()>0) {
            singerName += singerList.get(0).getSinName();
            for(int i = 1;i < singerList.size();i++) {
                singerName += "/" + singerList.get(i).getSinName();
            }
        }
        holder.singerName.setText(singerName);
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    class SongItemViewHolder extends RecyclerView.ViewHolder{

        TextView songPosition,songName,singerName;

        public SongItemViewHolder(@NonNull View itemView) {
            super(itemView);
            songPosition = itemView.findViewById(R.id.song_position);
            songName = itemView.findViewById(R.id.song_name);
            singerName = itemView.findViewById(R.id.singer_name);
        }
    }
}
