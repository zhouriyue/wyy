package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.Song;
import com.gxuwz.beethoven.util.WindowPixels;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    Context context;
    List<Song> songList;
    protected boolean isScrolling = false;

    public SongAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recsong,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        holder.songView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        holder.songView.setNestedScrollingEnabled(false);
        holder.songView.setAdapter(new SongItemAdapter(context,songList.subList(position*3,position*3+3)));
    }

    @Override
    public int getItemCount() {
        return songList.size()/3;
    }

    class SongViewHolder extends RecyclerView.ViewHolder{

        RecyclerView songView;
        LinearLayout recSongboxLin;
        int itemWidth = (int) ((WindowPixels.WIDTH-50)*WindowPixels.DENSITY);

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            recSongboxLin = itemView.findViewById(R.id.rec_songbox_lin);
            recSongboxLin.setMinimumWidth(itemWidth);
            recSongboxLin.setMinimumHeight(itemWidth/2);
            songView = itemView.findViewById(R.id.rec_songbox_rv);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
