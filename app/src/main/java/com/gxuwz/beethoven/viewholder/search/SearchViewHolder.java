package com.gxuwz.beethoven.viewholder.search;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

public class SearchViewHolder extends RecyclerView.ViewHolder {

    public RecyclerView searchItemRv;
    public TextView title,tag;
    public ImageView tagIcon;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        searchItemRv = itemView.findViewById(R.id.search_item_rv);
        title = itemView.findViewById(R.id.title);
        tag = itemView.findViewById(R.id.tag);
        tagIcon = itemView.findViewById(R.id.tag_icon);
    }
}
