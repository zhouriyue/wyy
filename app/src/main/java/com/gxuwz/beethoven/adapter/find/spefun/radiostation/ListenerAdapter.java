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
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStation;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStationSF;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class ListenerAdapter extends RecyclerView.Adapter<ListenerAdapter.ListenerViewHolder> {

    Context context;
    List<RadioStation> radioStationList;
    int itemWidth = (int) ((WindowPixels.WIDTH-60)/3);

    public ListenerAdapter(Context context, List<RadioStation> radioStationList) {
        this.context = context;
        this.radioStationList = radioStationList;
    }

    @NonNull
    @Override
    public ListenerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListenerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_srs_listener,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ListenerViewHolder holder, int position) {
        RadioStation radioStation = radioStationList.get(position);
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(radioStation.getImg(),context),itemWidth,itemWidth,5));
        holder.name.setText(radioStation.getName());
        holder.type.setText(radioStation.getType());
    }

    @Override
    public int getItemCount() {
        return radioStationList.size();
    }

    class ListenerViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView type,name;

        public ListenerViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            type = itemView.findViewById(R.id.rs_type);
            name = itemView.findViewById(R.id.name);
        }
    }
}
