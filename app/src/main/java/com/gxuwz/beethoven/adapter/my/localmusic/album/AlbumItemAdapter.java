package com.gxuwz.beethoven.adapter.my.localmusic.album;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.my.local.album.Album;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class AlbumItemAdapter extends RecyclerView.Adapter<AlbumItemAdapter.AlbumViewHolder> {

    Context context;
    List<Album> albumList;

    public AlbumItemAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(context).inflate(R.layout.collect_album_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.albumdiagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(album.getAlbumDiagonal(),context),48,48,5));
        holder.albumTitle.setText(album.getAlbumTitle());
        holder.singerName.setText(album.getSingerName());
        holder.count.setText(album.getCount()+"首");
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder{

        ImageView albumdiagonal;
        TextView albumTitle,count,singerName;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            albumdiagonal = itemView.findViewById(R.id.album_diagonal);
            albumTitle = itemView.findViewById(R.id.album_title);
            count = itemView.findViewById(R.id.count);
            singerName = itemView.findViewById(R.id.singer_name);
        }
    }
}
