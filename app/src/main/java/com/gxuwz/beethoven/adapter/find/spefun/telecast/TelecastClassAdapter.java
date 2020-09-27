package com.gxuwz.beethoven.adapter.find.spefun.telecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.spefun.telecast.TelecastClass;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class TelecastClassAdapter extends RecyclerView.Adapter<TelecastClassAdapter.TelecastClassViewHolder> {

    Context context;
    List<TelecastClass> telecastClassList;

    public TelecastClassAdapter(Context context, List<TelecastClass> telecastClassList) {
        this.context = context;
        this.telecastClassList = telecastClassList;
    }

    @NonNull
    @Override
    public TelecastClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TelecastClassViewHolder(LayoutInflater.from(context).inflate(R.layout.item_telecast_class,null));
    }

    @Override
    public void onBindViewHolder(@NonNull TelecastClassViewHolder holder, int position) {
        TelecastClass telecastClass = telecastClassList.get(position);
        holder.icon.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(telecastClass.getIcon(),context)));
        holder.name.setText(telecastClass.getName());
    }

    @Override
    public int getItemCount() {
        return telecastClassList.size();
    }

    class TelecastClassViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView name;
        int itemWidth = (int) ((WindowPixels.WIDTH-100)/5);

        public TelecastClassViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            icon.setMaxWidth(itemWidth);
            icon.setMaxHeight(itemWidth);
            name = itemView.findViewById(R.id.name);
        }
    }
}
