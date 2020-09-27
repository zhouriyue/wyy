package com.gxuwz.beethoven.adapter.find.spefun.rankinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.RankingList;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class RowShowAdapter extends RecyclerView.Adapter<RowShowAdapter.RowShowViewHolder> {

    Context context;
    List<RankingList> rankingListList;
    int itemWidth = (int) (WindowPixels.WIDTH-60)/3;

    public RowShowAdapter(Context context, List<RankingList> rankingListList) {
        this.context = context;
        this.rankingListList = rankingListList;
    }

    @NonNull
    @Override
    public RowShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RowShowViewHolder(LayoutInflater.from(context).inflate(R.layout.row_show_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RowShowViewHolder holder, int position) {
        RankingList rankingList = rankingListList.get(position);
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(rankingList.getImg(),context),itemWidth,itemWidth,5));
        holder.rankingListTitle.setText(rankingList.getName());
        holder.updateType.setText(rankingList.getUpdateType());
    }

    @Override
    public int getItemCount() {
        return rankingListList.size();
    }

    class RowShowViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView updateType,rankingListTitle;

        public RowShowViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            updateType = itemView.findViewById(R.id.update_type);
            rankingListTitle = itemView.findViewById(R.id.rankinglist_title);
        }
    }
}
