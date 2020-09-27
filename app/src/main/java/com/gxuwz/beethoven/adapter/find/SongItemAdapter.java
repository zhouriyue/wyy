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
import com.gxuwz.beethoven.model.entity.Song;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

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
        return new SongItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recsong_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SongItemViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.image.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(song.getImage(),context),48,48,5));
        holder.detail.setText(song.getDetail());
        holder.songName.setText(song.getSongName());
        holder.singerName.setText(song.getSinger());
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    class SongItemViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView songName,singerName,detail;

        public SongItemViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            songName = itemView.findViewById(R.id.song_name);
            singerName = itemView.findViewById(R.id.singer_name);
            detail = itemView.findViewById(R.id.detail);
        }
    }
}
