package com.gxuwz.beethoven.viewholder.find.spefun.radiostation;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.util.WindowPixels;

public class BannerViewHolder extends RecyclerView.ViewHolder{

    public View banner;

    public BannerViewHolder(@NonNull View itemView) {
        super(itemView);
        banner = itemView.findViewById(R.id.banner);
        banner.setMinimumWidth((int) ((int) (WindowPixels.WIDTH-20)*WindowPixels.DENSITY));
        banner.setMinimumHeight((int) (125*WindowPixels.DENSITY));
    }
}
