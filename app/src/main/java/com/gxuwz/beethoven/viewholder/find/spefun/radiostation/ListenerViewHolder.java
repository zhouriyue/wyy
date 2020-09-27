package com.gxuwz.beethoven.viewholder.find.spefun.radiostation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

public class ListenerViewHolder extends RecyclerView.ViewHolder {

    public ImageView img;
    public TextView type,name;

    public ListenerViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
        type = itemView.findViewById(R.id.rs_type);
        name = itemView.findViewById(R.id.name);
    }
}
