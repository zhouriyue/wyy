package com.gxuwz.beethoven.adapter.find.musiccal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.musiccal.MusicCal;

import java.util.List;

public class McAdapter extends RecyclerView.Adapter<McAdapter.McViewHolder> {

    Context context;
    List<MusicCal> musicCalList;

    public McAdapter(Context context, List<MusicCal> musicCalList) {
        this.context = context;
        this.musicCalList = musicCalList;
    }

    @NonNull
    @Override
    public McViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new McViewHolder(LayoutInflater.from(context).inflate(R.layout.mc_content_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull McViewHolder holder, int position) {
        MusicCal musicCal = musicCalList.get(position);
        holder.mcContentRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        holder.mcContentRv.setAdapter(new McContentAdapter(context,musicCal.getMusicCalSongList()));
    }

    @Override
    public int getItemCount() {
        return musicCalList.size();
    }

    class McViewHolder extends RecyclerView.ViewHolder{

        TextView time;
        RecyclerView mcContentRv;

        public McViewHolder(@NonNull View itemView) {
            super(itemView);
            mcContentRv = itemView.findViewById(R.id.mc_content_rv);
            time = itemView.findViewById(R.id.time);
        }
    }
}
