package com.gxuwz.beethoven.adapter.my.collect.likeyunvillage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.my.collect.YunVillage;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class LikeYunVillageAdapter extends RecyclerView.Adapter<LikeYunVillageAdapter.LyvViewHolder> {

    Context context;
    List<YunVillage> yunVillageList;

    public LikeYunVillageAdapter(Context context, List<YunVillage> yunVillageList) {
        this.context = context;
        this.yunVillageList = yunVillageList;
    }

    @NonNull
    @Override
    public LyvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LyvViewHolder(LayoutInflater.from(context).inflate(R.layout.collect_yunvillage_like_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull LyvViewHolder holder, int position) {
        YunVillage yunVillage = yunVillageList.get(position);
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtils.getRes(yunVillage.getDiagonal(),context),60,60,10));
        holder.title.setText(yunVillage.getTitle());
        holder.content.setText(yunVillage.getContent());
    }

    @Override
    public int getItemCount() {
        return yunVillageList.size();
    }

    class LyvViewHolder extends RecyclerView.ViewHolder{

        ImageView diagonal;
        TextView title,content;

        public LyvViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }
}
