package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.RankingList;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class RankingListAdapter extends RecyclerView.Adapter<RankingListAdapter.RankingListViewHolder> {

    Context context;
    List<RankingList> rankingListList;
    int itemWidth = (int) ((WindowPixels.WIDTH-60)*WindowPixels.DENSITY);
    protected boolean isScrolling = false;

    public RankingListAdapter(Context context, List<RankingList> rankingListList) {
        this.context = context;
        this.rankingListList = rankingListList;
    }

    @NonNull
    @Override
    public RankingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RankingListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rankinglist,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RankingListViewHolder holder, int position) {
        RankingList rankingList = rankingListList.get(position);
        holder.rankingListName.setText(rankingList.getName());
        holder.rlItemRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        holder.rlItemRv.setNestedScrollingEnabled(false);
        holder.rlItemRv.setAdapter(new RLItemAdapter(context,rankingList.getSongList()));
        holder.rankinglistBg.setBackground(new BitmapDrawable(MergeImage.roundedCustomDB(HttpUtils.getRes(rankingList.getImg(),context),itemWidth,itemWidth/2,40)));
    }

    @Override
    public int getItemCount() {
        return rankingListList.size();
    }

    class RankingListViewHolder extends RecyclerView.ViewHolder{

        LinearLayout rankinglistBg;
        RelativeLayout rankingListRl;
        TextView rankingListName;
        RecyclerView rlItemRv;


        public RankingListViewHolder(@NonNull View itemView) {
            super(itemView);
            rankinglistBg = itemView.findViewById(R.id.rankinglist_bg);
            rankingListRl = itemView.findViewById(R.id.ranking_list_rl);
            rankingListRl.setMinimumWidth(itemWidth);
            rankingListRl.setMinimumHeight(itemWidth/2);
            rlItemRv = itemView.findViewById(R.id.ranking_list_item_rv);
            rankingListName = itemView.findViewById(R.id.ranking_list_name);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
