package com.gxuwz.beethoven.adapter.find.spefun.radiostation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStationSF;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class RSSFAdapter extends RecyclerView.Adapter<RSSFAdapter.RSSFViewHolder> {

    Context context;
    List<RadioStationSF> radioStationSFList;

    public RSSFAdapter(Context context, List<RadioStationSF> radioStationSFList) {
        this.context = context;
        this.radioStationSFList = radioStationSFList;
    }

    @NonNull
    @Override
    public RSSFViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RSSFViewHolder(LayoutInflater.from(context).inflate(R.layout.item_srs_sf,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RSSFViewHolder holder, int position) {
        RadioStationSF stationSF = radioStationSFList.get(position);
        holder.icon.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(stationSF.getIcon(),context)));
        holder.sfName.setText(stationSF.getSfName());
    }

    @Override
    public int getItemCount() {
        return radioStationSFList.size();
    }

    class RSSFViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView sfName;

        public RSSFViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            sfName = itemView.findViewById(R.id.sf_name);
        }
    }
}
