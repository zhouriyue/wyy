package com.gxuwz.beethoven.adapter.find.musiccal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.musiccal.MusicCalSong;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class McContentAdapter extends RecyclerView.Adapter<McContentAdapter.McContentViewHolder> {

    Context context;
    List<MusicCalSong> musicCalSongList;

    public McContentAdapter(Context context, List<MusicCalSong> musicCalSongList) {
        this.context = context;
        this.musicCalSongList = musicCalSongList;
    }

    @NonNull
    @Override
    public McContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new McContentViewHolder(LayoutInflater.from(context).inflate(R.layout.content_box,null));
    }

    @Override
    public void onBindViewHolder(@NonNull McContentViewHolder holder, int position) {
        MusicCalSong musicCalSong = musicCalSongList.get(position);
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtils.getRes(musicCalSong.getSong().getImage(),context),48,48,5));
        holder.title.setText(musicCalSong.getTitle());
    }

    @Override
    public int getItemCount() {
        return musicCalSongList.size();
    }

    class McContentViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView title;

        public McContentViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
        }
    }
}
