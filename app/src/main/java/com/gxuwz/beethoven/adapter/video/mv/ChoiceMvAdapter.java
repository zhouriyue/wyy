package com.gxuwz.beethoven.adapter.video.mv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.video.mv.ChoiceMv;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class ChoiceMvAdapter extends RecyclerView.Adapter<ChoiceMvAdapter.ChoiceMvViewHolder> {

    List<ChoiceMv> choiceMvList;
    Context context;

    int itemWidth;
    int height;

    public ChoiceMvAdapter(List<ChoiceMv> choiceMvList, Context context) {
        this.choiceMvList = choiceMvList;
        this.context = context;
        this.itemWidth = (int) ((WindowPixels.WIDTH-40)/2);
        this.height = (int) (itemWidth/1.7);
    }

    @NonNull
    @Override
    public ChoiceMvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChoiceMvViewHolder(LayoutInflater.from(context).inflate(R.layout.subpage_mv_choice_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceMvViewHolder holder, int position) {
        ChoiceMv choiceMv = choiceMvList.get(position);
        holder.diagonal.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(choiceMv.getDiagonal(),context),itemWidth,height));
        holder.playNumber.setText(choiceMv.getPlayNumber()+"");
        holder.content.setText(choiceMv.getContent());
        holder.username.setText(choiceMv.getUsername());
    }

    @Override
    public int getItemCount() {
        return choiceMvList.size();
    }

    public class ChoiceMvViewHolder extends RecyclerView.ViewHolder{

        ImageView diagonal;
        TextView playNumber,content,username;

        public ChoiceMvViewHolder(@NonNull View itemView) {
            super(itemView);
            diagonal = itemView.findViewById(R.id.diagonal);
            playNumber = itemView.findViewById(R.id.play_number);
            content = itemView.findViewById(R.id.content);
            username = itemView.findViewById(R.id.username);
        }
    }
}
