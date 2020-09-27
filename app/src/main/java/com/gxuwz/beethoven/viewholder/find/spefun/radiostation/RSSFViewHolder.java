package com.gxuwz.beethoven.viewholder.find.spefun.radiostation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

public class RSSFViewHolder extends RecyclerView.ViewHolder {

    public ImageView icon;
    public TextView sfName;

    public RSSFViewHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.icon);
        sfName = itemView.findViewById(R.id.sf_name);
    }
}
