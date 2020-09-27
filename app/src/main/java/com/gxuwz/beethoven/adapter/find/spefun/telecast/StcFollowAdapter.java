package com.gxuwz.beethoven.adapter.find.spefun.telecast;

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
import com.gxuwz.beethoven.model.entity.find.Telecast;
import com.gxuwz.beethoven.model.entity.find.spefun.telecast.TelecastFollowView;

import java.util.List;

public class StcFollowAdapter extends RecyclerView.Adapter<StcFollowAdapter.StcFollowViewHolder> {

    Context context;
    List<TelecastFollowView> telecastFollowViews;

    public StcFollowAdapter(Context context, List<TelecastFollowView> telecastFollowViews) {
        this.context = context;
        this.telecastFollowViews = telecastFollowViews;
    }

    @NonNull
    @Override
    public StcFollowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StcFollowViewHolder(LayoutInflater.from(context).inflate(R.layout.item_stc_follow,null));
    }

    @Override
    public void onBindViewHolder(@NonNull StcFollowViewHolder holder, int position) {
        TelecastFollowView telecastFollowView = telecastFollowViews.get(position);
        holder.title.setText(telecastFollowView.getTitle());
        holder.itemStcRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        if(telecastFollowView.getShowType()==1) {
            holder.itemStcRv.setAdapter(new FollowAdapter(context,telecastFollowView.getTelecastList()));
        } else {
            holder.itemStcRv.setAdapter(new MoreRecommendAdapter(context,telecastFollowView.getTelecastList()));
        }
    }

    @Override
    public int getItemCount() {
        return telecastFollowViews.size();
    }

    class StcFollowViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        RecyclerView itemStcRv;

        public StcFollowViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            itemStcRv = itemView.findViewById(R.id.item_stc_rv);
        }
    }
}
