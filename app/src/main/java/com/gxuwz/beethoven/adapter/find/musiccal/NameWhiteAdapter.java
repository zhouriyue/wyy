package com.gxuwz.beethoven.adapter.find.musiccal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

import java.util.List;

public class NameWhiteAdapter extends RecyclerView.Adapter<NameWhiteAdapter.NameWhiteViewHolder> {

    Context context;
    List<String> viewNameList;

    public NameWhiteAdapter(Context context, List<String> viewNameList) {
        this.context = context;
        this.viewNameList = viewNameList;
    }

    @NonNull
    @Override
    public NameWhiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NameWhiteViewHolder(LayoutInflater.from(context).inflate(R.layout.name_white_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull NameWhiteViewHolder holder, int position) {
        holder.viewName.setText(viewNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return viewNameList.size();
    }

    class NameWhiteViewHolder extends RecyclerView.ViewHolder {

        TextView viewName;

        public NameWhiteViewHolder(@NonNull View itemView) {
            super(itemView);
            viewName = itemView.findViewById(R.id.view_name);
        }
    }
}
