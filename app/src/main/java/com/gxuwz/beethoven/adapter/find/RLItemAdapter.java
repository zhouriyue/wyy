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

public class RLItemAdapter extends RecyclerView.Adapter<RLItemAdapter.RLItemViewHolder> {

    Context context;
    List<Song> songList;

    public RLItemAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public RLItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RLItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rankinglist_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RLItemViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.image.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(song.getImage(),context),48,48,5));
        if(song.getIsNew()==1) {
            holder.type.setText("新");
        } else {
            holder.type.setText("");
        }
        holder.singerName.setText(song.getSinger());
        holder.songName.setText(song.getSongName());
        holder.positiontv.setText((position+1)+"");
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    class RLItemViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView positiontv,songName,singerName,type;

        public RLItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            positiontv = itemView.findViewById(R.id.position);
            songName = itemView.findViewById(R.id.song_name);
            singerName = itemView.findViewById(R.id.singer_name);
            type = itemView.findViewById(R.id.type);
        }
    }
}
