package com.gxuwz.beethoven.viewholder.morefun;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

public class FunViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView funName,status;

    public FunViewHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.icon);
        funName = itemView.findViewById(R.id.fun_name);
        status = itemView.findViewById(R.id.status);
    }
}
