package com.gxuwz.beethoven.adapter.morefun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.Fun;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.viewholder.morefun.FunViewHolder;

import java.util.List;

public class FunAdapter extends RecyclerView.Adapter<FunViewHolder> {

    Context context;
    List<Fun> funList;

    public FunAdapter(Context context, List<Fun> funList) {
        this.context = context;
        this.funList = funList;
    }

    @NonNull
    @Override
    public FunViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FunViewHolder(LayoutInflater.from(context).inflate(R.layout.item_fun,null));
    }

    @Override
    public void onBindViewHolder(@NonNull FunViewHolder holder, int position) {
        Fun fun = funList.get(position);
        holder.icon.setImageBitmap(MergeImage.roundedCustomDB(HttpUtils.getRes(fun.getIcon(),context),20,20,1));
        holder.funName.setText(fun.getName());
        holder.status.setText(fun.getStaus());
    }

    @Override
    public int getItemCount() {
        return funList.size();
    }
}
