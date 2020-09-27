package com.gxuwz.beethoven.adapter.video.telecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagViewHolder> {

    Context context;
    List<String> tagList;

    public TagAdapter(Context context, List<String> tagList) {
        this.context = context;
        this.tagList = tagList;
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TagViewHolder(LayoutInflater.from(context).inflate(R.layout.subpage_telecast_tag_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        holder.tagStr.setText(tagList.get(position));
    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    public class TagViewHolder extends RecyclerView.ViewHolder{

        TextView tagStr;

        public TagViewHolder(@NonNull View itemView) {
            super(itemView);
            tagStr = itemView.findViewById(R.id.tag_str);
        }
    }
}
