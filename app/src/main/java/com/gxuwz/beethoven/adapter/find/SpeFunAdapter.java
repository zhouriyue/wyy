package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.SpecialFun;
import com.gxuwz.beethoven.page.index.findview.spefun.everyrecomm.EveryRecommActivity;
import com.gxuwz.beethoven.page.index.findview.spefun.radiostation.SRadioStationActivity;
import com.gxuwz.beethoven.page.index.findview.spefun.slplaza.SLPlazaActivity;
import com.gxuwz.beethoven.page.index.findview.spefun.rankinglist.RankingListActivity;
import com.gxuwz.beethoven.page.index.findview.spefun.telecast.STelecastActivity;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

/**
 * 推荐歌单adapter
 */
public class SpeFunAdapter extends RecyclerView.Adapter<SpeFunAdapter.RSLViewHolder> {

    Context context;
    List<SpecialFun> specialFunList;
    protected boolean isScrolling = false;

    public SpeFunAdapter(Context context, List<SpecialFun> specialFunList) {
        this.context = context;
        this.specialFunList = specialFunList;
    }

    @NonNull
    @Override
    public RSLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RSLViewHolder(LayoutInflater.from(context).inflate(R.layout.item_spefun,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RSLViewHolder holder, int position) {
        SpecialFun specialFun = specialFunList.get(position);
        holder.speFunLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                switch (specialFun.getPath()) {
                    case "everyrecomm":{
                        intent = new Intent(context, EveryRecommActivity.class);
                    };break;
                    case "slplaza":{
                        intent = new Intent(context, SLPlazaActivity.class);
                    };break;
                    case "rankinglist":{
                        intent = new Intent(context, RankingListActivity.class);
                    };break;
                    case "sradiostation":{
                        intent = new Intent(context, SRadioStationActivity.class);
                    };break;
                    case "stelecast":{
                        intent = new Intent(context, STelecastActivity.class);
                    };break;
                }
                context.startActivity(intent);
            }
        });
        holder.icon.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(specialFun.getIcon(),context)));
        holder.title.setText(specialFun.getTitle());
    }

    @Override
    public int getItemCount() {
        return specialFunList.size();
    }

    class RSLViewHolder extends RecyclerView.ViewHolder{

        LinearLayout speFunLin;
        ImageView icon;
        TextView title;

        public RSLViewHolder(@NonNull View itemView) {
            super(itemView);
            speFunLin = itemView.findViewById(R.id.spe_fun_lin);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
