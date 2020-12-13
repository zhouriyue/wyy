package com.gxuwz.beethoven.adapter.find.spefun.rankinglist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.current.RankingList;
import com.gxuwz.beethoven.page.fragment.find.RankingMainActivity;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.io.Serializable;
import java.util.List;

public class ColShowAdapter extends RecyclerView.Adapter<ColShowAdapter.ColShowViewHolder> {

    Context context;
    List<RankingList> rankingListList;
    int itemWidth = (int) (WindowPixels.WIDTH)/3;

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
        MergeImage.showGlideImgDb(context, StaticHttp.STATIC_BASEURL+rankingList.getSlPicture(),holder.img,5);
        holder.updateType.setText(rankingList.getRlName());
        holder.songList.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        holder.songList.setAdapter(new SongItemAdapter(context,rankingList.getSongList().subList(0,3)));
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

    class ColShowViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout pId;
        ImageView img;
        TextView updateType;
        RecyclerView songList;

        public ColShowViewHolder(@NonNull View itemView) {
            super(itemView);
            pId = itemView.findViewById(R.id.p_id);
            img = itemView.findViewById(R.id.img);
            img.setMaxWidth(itemWidth);
            img.setMaxHeight(itemWidth);
            img.setMinimumWidth(itemWidth);
            img.setMinimumHeight(itemWidth);
            updateType = itemView.findViewById(R.id.update_type);
            songList = itemView.findViewById(R.id.song_list);
        }
    }
}
