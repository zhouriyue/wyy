package com.gxuwz.beethoven.adapter.find.spefun.radiostation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.StationsClass;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class StationClassAdapter extends RecyclerView.Adapter<StationClassAdapter.StationClassViewHolder> {

    Context context;
    List<StationsClass> stationsClassList;

    public StationClassAdapter(Context context, List<StationsClass> stationsClassList) {
        this.context = context;
        this.stationsClassList = stationsClassList;
    }

    @NonNull
    @Override
    public StationClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StationClassViewHolder(LayoutInflater.from(context).inflate(R.layout.item_srs_stationclass,null));
    }

    @Override
    public void onBindViewHolder(@NonNull StationClassViewHolder holder, int position) {
        StationsClass stationsClass = stationsClassList.get(position);
        holder.icon.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(stationsClass.getIcon(),context),30,30,1));
        holder.name.setText(stationsClass.getClassName());
    }

    @Override
    public int getItemCount() {
        return stationsClassList.size();
    }

    class StationClassViewHolder extends RecyclerView.ViewHolder{

        LinearLayout classLin;
        ImageView icon;
        TextView name;
        int itemWidth = (int) ((WindowPixels.WIDTH-20)*WindowPixels.DENSITY/2);
        int itemHeight = (int) (60*WindowPixels.DENSITY);

        public StationClassViewHolder(@NonNull View itemView) {
            super(itemView);
            classLin = itemView.findViewById(R.id.class_lin);
            classLin.setMinimumWidth(itemWidth);
            classLin.setMinimumHeight(itemHeight);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
        }
    }
}
