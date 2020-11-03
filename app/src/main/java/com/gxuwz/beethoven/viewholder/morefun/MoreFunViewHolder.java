package com.gxuwz.beethoven.viewholder.morefun;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

public class MoreFunViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public RecyclerView funRv;

    public MoreFunViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        funRv = itemView.findViewById(R.id.fun_rv);
    }
}
