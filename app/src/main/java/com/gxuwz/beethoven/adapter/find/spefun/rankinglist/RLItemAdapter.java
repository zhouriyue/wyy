package com.gxuwz.beethoven.adapter.find.spefun.rankinglist;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.spefun.rankinglist.RankingListShow;

import java.util.List;

public class RLItemAdapter extends RecyclerView.Adapter<RLItemAdapter.RLItemViewHolder> {

    Context context;
    List<RankingListShow> rklShowList;

    public RLItemAdapter(Context context, List<RankingListShow> rklShowList) {
        this.context = context;
        this.rklShowList = rklShowList;
    }

    @NonNull
    @Override
    public RLItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RLItemViewHolder(LayoutInflater.from(context).inflate(R.layout.rankinglist_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RLItemViewHolder holder, int position) {
        RankingListShow rankingListShow = rklShowList.get(position);
        holder.title.setText(rankingListShow.getTitle());
        if(rankingListShow.getShowType()==1) {
            holder.item.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
            holder.item.setAdapter(new RowShowAdapter(context,rankingListShow.getRankingListList()));
        } else {
            holder.item.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            holder.item.setAdapter(new ColShowAdapter(context,rankingListShow.getRankingListList()));
        }
    }

    @Override
    public int getItemCount() {
        return rklShowList.size();
    }

    class RLItemViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        RecyclerView item;

        public RLItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            title.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            item = itemView.findViewById(R.id.ranking_list_item);
        }
    }
}
