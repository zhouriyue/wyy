package com.gxuwz.beethoven.adapter.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

import java.util.List;

public class NameViewAdapter extends RecyclerView.Adapter<NameViewAdapter.NameViewHolder>{

    List<String> viewNameList;
    Context context;

    public NameViewAdapter(List<String> viewNameList,Context context) {
        this.viewNameList = viewNameList;
        this.context = context;
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NameViewHolder(LayoutInflater.from(context).inflate(R.layout.viewname_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        holder.viewName.setText(viewNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return viewNameList.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        TextView viewName;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
            viewName = itemView.findViewById(R.id.view_name);
        }
    }
}
