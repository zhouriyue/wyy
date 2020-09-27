package com.gxuwz.beethoven.adapter.my.collect.specialcolumn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.my.collect.SpecialColumn;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class RecommendSCAdapter extends RecyclerView.Adapter<RecommendSCAdapter.RecommendSCViewHolder> {

    Context context;
    List<SpecialColumn> specialColumnList;

    public RecommendSCAdapter(Context context, List<SpecialColumn> specialColumnList) {
        this.context = context;
        this.specialColumnList = specialColumnList;
    }

    @NonNull
    @Override
    public RecommendSCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendSCViewHolder(LayoutInflater.from(context).inflate(R.layout.collect_specialcolumn_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendSCViewHolder holder, int position) {
        SpecialColumn specialColumn = specialColumnList.get(position);
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(specialColumn.getDiagonal(),context),150,90,5));
        holder.readNumber.setText(specialColumn.getReadNumber()+"万");
        holder.content.setText(specialColumn.getContent());
    }

    @Override
    public int getItemCount() {
        return specialColumnList.size();
    }

    class RecommendSCViewHolder extends RecyclerView.ViewHolder{

        ImageView diagonal;
        TextView readNumber,content;

        public RecommendSCViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            readNumber = itemView.findViewById(R.id.read_number);
            content = itemView.findViewById(R.id.content);
        }
    }
}
