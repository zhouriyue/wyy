package com.gxuwz.beethoven.adapter.find.spefun.rankinglist;

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
import com.gxuwz.beethoven.model.entity.current.RankingList;
import com.gxuwz.beethoven.page.fragment.find.RankingMainActivity;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.io.Serializable;
import java.util.List;

public class RowShowAdapter extends RecyclerView.Adapter<RowShowAdapter.RowShowViewHolder> {

    Context context;
    List<RankingList> rankingListList;
    int itemWidth = (int) (WindowPixels.WIDTH)/3;

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
        MergeImage.showGlideImgDb(context, StaticHttp.STATIC_BASEURL+rankingList.getSlPicture(),holder.img,5);
        holder.rankingListTitle.setText(rankingList.getRlName());
        holder.updateType.setText(rankingList.getRlName());
        holder.pId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RankingMainActivity.class);
                intent.putExtra("rankingList", (Serializable) rankingList);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rankingListList.size();
    }

    class RowShowViewHolder extends RecyclerView.ViewHolder{

        LinearLayout pId;
        ImageView img;
        TextView updateType,rankingListTitle;

        public RowShowViewHolder(@NonNull View itemView) {
            super(itemView);
            pId = itemView.findViewById(R.id.p_id);
            img = itemView.findViewById(R.id.img);
            img.setMaxWidth(itemWidth);
            img.setMaxHeight(itemWidth);
            img.setMinimumWidth(itemWidth);
            img.setMinimumHeight(itemWidth);
            updateType = itemView.findViewById(R.id.update_type);
            rankingListTitle = itemView.findViewById(R.id.rankinglist_title);
        }
    }
}
