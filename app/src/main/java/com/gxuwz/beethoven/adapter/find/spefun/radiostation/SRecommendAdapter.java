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
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStation;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class SRecommendAdapter extends RecyclerView.Adapter<SRecommendAdapter.SRecommendViewHolder> {

    Context context;
    List<RadioStation> radioStationList;
    int itemWidth = (int) ((WindowPixels.WIDTH-60)/3);
    /**
     * isShowCost：
     *    0 不显示价格 not show price
     *    1 显示价格 show price
     */
    int isShowCost=0;

    public SRecommendAdapter(Context context, List<RadioStation> radioStationList) {
        this.context = context;
        this.radioStationList = radioStationList;
    }

    public SRecommendAdapter(Context context, List<RadioStation> radioStationList, int isShowCost) {
        this.context = context;
        this.radioStationList = radioStationList;
        this.isShowCost = isShowCost;
    }

    @NonNull
    @Override
    public SRecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SRecommendViewHolder(LayoutInflater.from(context).inflate(R.layout.item_srs_recommend,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SRecommendViewHolder holder, int position) {
        RadioStation radioStation = radioStationList.get(position);
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtils.getRes(radioStation.getImg(),context),itemWidth,itemWidth,5));
        holder.name.setText(radioStation.getName());
        if(radioStation.getIsPay()==1) {
            holder.rsIsPay.setText("精品付费");
        }
        if(isShowCost==1) {
            holder.costLin.setVisibility(View.VISIBLE);
            holder.cost.setText(radioStation.getCost()+"");
        } else {
            holder.costLin.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return radioStationList.size();
    }

    class SRecommendViewHolder extends RecyclerView.ViewHolder{

        LinearLayout costLin;
        ImageView img;
        TextView name,rsIsPay,cost;

        public SRecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            rsIsPay = itemView.findViewById(R.id.rs_is_pay);
            costLin = itemView.findViewById(R.id.cost_lin);
            cost = itemView.findViewById(R.id.cost);
        }
    }
}
