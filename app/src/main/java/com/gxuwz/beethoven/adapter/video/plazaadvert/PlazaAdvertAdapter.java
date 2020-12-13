package com.gxuwz.beethoven.adapter.video.plazaadvert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.video.plazaadvert.PlazaAdvert;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class PlazaAdvertAdapter extends RecyclerView.Adapter<PlazaAdvertAdapter.PlazaAdvertViewHolder> {

    Context context;
    List<PlazaAdvert> plazaAdvertList;
    int itemWidth;
    int height;

    public PlazaAdvertAdapter(Context context, List<PlazaAdvert> plazaAdvertList) {
        this.context = context;
        this.plazaAdvertList = plazaAdvertList;
        this.itemWidth = (int) WindowPixels.WIDTH-20;
        this.height = (int) (itemWidth/2.2);
    }

    @NonNull
    @Override
    public PlazaAdvertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlazaAdvertViewHolder(LayoutInflater.from(context).inflate(R.layout.subpage_plazaadvert_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull PlazaAdvertViewHolder holder, int position) {
        PlazaAdvert plazaAdvert = plazaAdvertList.get(position);
        holder.title.setText(plazaAdvert.getTitle());
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtils.getRes(plazaAdvert.getDiagonal(),context),itemWidth,height));
        holder.content.setText(plazaAdvert.getContent());
    }

    @Override
    public int getItemCount() {
        return plazaAdvertList.size();
    }

    public class PlazaAdvertViewHolder extends RecyclerView.ViewHolder{
        ImageView diagonal;
        TextView title,content;
        public PlazaAdvertViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }
}
