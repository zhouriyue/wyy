package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

import java.util.ArrayList;

public class SLPNameAdapter extends RecyclerView.Adapter<SLPNameAdapter.SLPNameViewHolder> {

    Context context;
    ArrayList<String> nameList;

    public SLPNameAdapter(Context context, ArrayList<String> nameList) {
        this.context = context;
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public SLPNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SLPNameViewHolder(LayoutInflater.from(context).inflate(R.layout.viewname_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SLPNameViewHolder holder, int position) {
        holder.viewName.setText(nameList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    class SLPNameViewHolder extends RecyclerView.ViewHolder{

        TextView viewName;

        public SLPNameViewHolder(@NonNull View itemView) {
            super(itemView);
            viewName = itemView.findViewById(R.id.view_name);
        }
    }
}
