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
import com.gxuwz.beethoven.model.entity.find.Telecast;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class TelecastAdapter extends RecyclerView.Adapter<TelecastAdapter.TelecastViewHolder> {

    Context context;
    List<Telecast> telecastList;
    int itemWidth = (int) ((WindowPixels.WIDTH-40)/2);

    public TelecastAdapter(Context context, List<Telecast> telecastList) {
        this.context = context;
        this.telecastList = telecastList;
    }

    @NonNull
    @Override
    public TelecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TelecastViewHolder(LayoutInflater.from(context).inflate(R.layout.item_stc_telecast,null));
    }

    @Override
    public void onBindViewHolder(@NonNull TelecastViewHolder holder, int position) {
        Telecast telecast = telecastList.get(position);
        holder.onlineNumber.setText(telecast.getOnlineNumber()+"");
        holder.title.setText(telecast.getTitle());
        holder.tagType.setText(telecast.getType());
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(telecast.getImg(),context),itemWidth,itemWidth,5));
    }

    @Override
    public int getItemCount() {
        return telecastList.size();
    }

    class TelecastViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView tagType,title,onlineNumber;

        public TelecastViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tagType = itemView.findViewById(R.id.tag_type);
            title = itemView.findViewById(R.id.title);
            onlineNumber = itemView.findViewById(R.id.online_number);
        }
    }
}
