package com.gxuwz.beethoven.viewholder.find.spefun.radiostation;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.util.WindowPixels;

public class StationClassViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout classLin;
    public ImageView icon;
    public TextView name;
    public int itemWidth = (int) ((WindowPixels.WIDTH-20)*WindowPixels.DENSITY/2);
    public int itemHeight = (int) (60*WindowPixels.DENSITY);

    public StationClassViewHolder(@NonNull View itemView) {
        super(itemView);
        classLin = itemView.findViewById(R.id.class_lin);
        classLin.setMinimumWidth(itemWidth);
        classLin.setMinimumHeight(itemHeight);
        icon = itemView.findViewById(R.id.icon);
        name = itemView.findViewById(R.id.name);
    }
}
