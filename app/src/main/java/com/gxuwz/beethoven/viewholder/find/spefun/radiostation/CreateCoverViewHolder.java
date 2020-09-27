package com.gxuwz.beethoven.viewholder.find.spefun.radiostation;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

public class CreateCoverViewHolder extends RecyclerView.ViewHolder {
    public ImageView img,perPic;
    public TextView title,username,hotNumber;

    public CreateCoverViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
        perPic = itemView.findViewById(R.id.per_pic);
        title = itemView.findViewById(R.id.title);
        username = itemView.findViewById(R.id.username);
        hotNumber = itemView.findViewById(R.id.hot_number);
    }
}
