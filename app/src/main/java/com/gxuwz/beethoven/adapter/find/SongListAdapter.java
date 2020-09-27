package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.page.index.findview.songlist.SongListFActivity;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongListViewHolder> {

    Context context;
    List<SongList> songLists;
    protected boolean isScrolling = false;

    public SongListAdapter(Context context, List<SongList> songLists) {
        this.context = context;
        this.songLists = songLists;
    }

    @NonNull
    @Override
    public SongListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recsl,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SongListViewHolder holder, int position) {
        SongList songList = songLists.get(position);
        holder.slImg.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(songList.getSongListUrl(),context),100,100));
        holder.playNumber.setText(songList.getPlayNumber()+"");
        holder.slName.setText(songList.getSongListName());
        holder.onclickSongList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SongListFActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songLists.size();
    }

    class SongListViewHolder extends RecyclerView.ViewHolder{

        LinearLayout onclickSongList;
        ImageView slImg;
        TextView playNumber,slName;

        public SongListViewHolder(@NonNull View itemView) {
            super(itemView);
            onclickSongList = itemView.findViewById(R.id.onclick_songlist);
            slImg = itemView.findViewById(R.id.sl_img);
            playNumber = itemView.findViewById(R.id.play_number);
            slName = itemView.findViewById(R.id.sl_name);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
