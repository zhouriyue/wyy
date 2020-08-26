package com.gxuwz.beethoven.adapter.my.follow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.my.follow.Song;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongListViewHolder> {

    Context context;
    List<Song> songList;

    public SongListAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongListViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_my_follow_song_list_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SongListViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(song.getImg(),context),38,38,5));
        holder.name.setText(song.getName());
        holder.detail.setText(song.getDetail());
        holder.singers.setText(song.getSingles());
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    class SongListViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,detail,singers;

        public SongListViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            detail = itemView.findViewById(R.id.detail);
            singers = itemView.findViewById(R.id.singers);
        }
    }
}
