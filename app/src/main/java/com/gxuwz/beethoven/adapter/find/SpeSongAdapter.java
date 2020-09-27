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
import com.gxuwz.beethoven.model.entity.find.SpeSong;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class SpeSongAdapter extends RecyclerView.Adapter<SpeSongAdapter.SpeSongViewHolder> {

    Context context;
    List<SpeSong> speSongList;
    protected boolean isScrolling = false;

    public SpeSongAdapter(Context context, List<SpeSong> speSongList) {
        this.context = context;
        this.speSongList = speSongList;
    }

    @NonNull
    @Override
    public SpeSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SpeSongViewHolder(LayoutInflater.from(context).inflate(R.layout.item_spesong,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SpeSongViewHolder holder, int position) {
        SpeSong speSong = speSongList.get(position);
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(speSong.getImage(),context),100,100,5));
        holder.name.setText(speSong.getName());
        holder.title.setText(speSong.getTitle());
    }

    @Override
    public int getItemCount() {
        return speSongList.size();
    }

    class SpeSongViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,title;

        public SpeSongViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            title = itemView.findViewById(R.id.title);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
