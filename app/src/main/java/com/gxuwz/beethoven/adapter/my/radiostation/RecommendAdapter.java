package com.gxuwz.beethoven.adapter.my.radiostation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.my.radiostation.RecommendItem;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {

    Context context;
    List<RecommendItem> recommendItemList;
    int itemWidth;
    int height;

    public RecommendAdapter(Context context, List<RecommendItem> recommendItemList) {
        this.context = context;
        this.recommendItemList = recommendItemList;
        this.itemWidth = (int) ((WindowPixels.WIDTH-60)/3);
        this.height = itemWidth;
    }

    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_my_radiostation_recommend_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendViewHolder holder, int position) {
        RecommendItem recommendItem = recommendItemList.get(position);
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(recommendItem.getDiagonal(),context),itemWidth,height,5));
        holder.content.setText(recommendItem.getContent());
    }

    @Override
    public int getItemCount() {
        return recommendItemList.size();
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder{

        ImageView diagonal;
        TextView content;

        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            content = itemView.findViewById(R.id.content);
        }
    }
}
