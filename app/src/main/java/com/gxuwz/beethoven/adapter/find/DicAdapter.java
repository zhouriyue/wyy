package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.Dic;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class DicAdapter extends RecyclerView.Adapter<DicAdapter.DicViewHolder> {

    Context context;
    List<Dic> dicList;
    protected boolean isScrolling = false;

    public DicAdapter(Context context, List<Dic> dicList) {
        this.context = context;
        this.dicList = dicList;
    }

    @NonNull
    @Override
    public DicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DicViewHolder(LayoutInflater.from(context).inflate(R.layout.item_dic,null));
    }

    @Override
    public void onBindViewHolder(@NonNull DicViewHolder holder, int position) {
        holder.recDicboxRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        holder.recDicboxRv.setNestedScrollingEnabled(false);
        holder.recDicboxRv.setAdapter(new DicItemAdapter(context,dicList.subList(position*3,position*3+3)));
    }

    @Override
    public int getItemCount() {
        return dicList.size()/3;
    }

    class DicViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recDicboxRv;
        LinearLayout recDicboxLin;
        int itemWidth = (int) ((WindowPixels.WIDTH-50)* WindowPixels.DENSITY);

        public DicViewHolder(@NonNull View itemView) {
            super(itemView);
            recDicboxLin = itemView.findViewById(R.id.rec_dicbox_lin);
            recDicboxLin.setMinimumWidth(itemWidth);
            recDicboxLin.setMinimumHeight(itemWidth/2);
            recDicboxRv = itemView.findViewById(R.id.rec_dicbox_rv);
        }
    }

    public boolean isScrolling() {
        return isScrolling;
    }

    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
}
