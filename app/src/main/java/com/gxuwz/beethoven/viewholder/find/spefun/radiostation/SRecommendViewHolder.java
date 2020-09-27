package com.gxuwz.beethoven.viewholder.find.spefun.radiostation;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;

public class SRecommendViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout costLin;
    public ImageView img;
    public TextView name,rsIsPay,cost;

    public SRecommendViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.img);
        name = itemView.findViewById(R.id.name);
        rsIsPay = itemView.findViewById(R.id.rs_is_pay);
        costLin = itemView.findViewById(R.id.cost_lin);
        cost = itemView.findViewById(R.id.cost);
    }
}
