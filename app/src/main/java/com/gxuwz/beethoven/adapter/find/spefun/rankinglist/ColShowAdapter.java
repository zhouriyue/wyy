package com.gxuwz.beethoven.adapter.find.spefun.rankinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.RankingList;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class ColShowAdapter extends RecyclerView.Adapter<ColShowAdapter.ColShowViewHolder> {

    Context context;
    List<RankingList> rankingListList;
    int itemWidth = (int) (WindowPixels.WIDTH-60)/3;

    public ColShowAdapter(Context context, List<RankingList> rankingListList) {
        this.context = context;
        this.rankingListList = rankingListList;
    }

    @NonNull
    @Override
    public ColShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ColShowViewHolder(LayoutInflater.from(context).inflate(R.layout.col_show_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ColShowViewHolder holder, int position) {
        RankingList rankingList = rankingListList.get(position);
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(rankingList.getImg(),context),itemWidth,itemWidth,5));
        holder.updateType.setText(rankingList.getUpdateType());
        holder.songList.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        holder.songList.setAdapter(new SongItemAdapter(context,rankingList.getSongList()));
    }

    @Override
    public int getItemCount() {
        return rankingListList.size();
    }

    class ColShowViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView updateType;
        RecyclerView songList;

        public ColShowViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            updateType = itemView.findViewById(R.id.update_type);
            songList = itemView.findViewById(R.id.song_list);
        }
    }
}
