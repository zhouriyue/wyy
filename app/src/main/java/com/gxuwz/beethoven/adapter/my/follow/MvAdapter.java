package com.gxuwz.beethoven.adapter.my.follow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.my.follow.Mv;
import com.gxuwz.beethoven.model.entity.my.follow.Singer;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class MvAdapter extends RecyclerView.Adapter<MvAdapter.MvViewHolder> {

    Context context;
    List<Mv> mvList;
    int item;
    int height;

    public MvAdapter(Context context, List<Mv> mvList) {
        this.context = context;
        this.mvList = mvList;
        this.item = (int) (WindowPixels.WIDTH-20);
        this.height = (int) (item * 0.6);
    }

    @NonNull
    @Override
    public MvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MvViewHolder(LayoutInflater.from(context).inflate(R.layout.follow_mv_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MvViewHolder holder, int position) {
        Mv mv = mvList.get(position);
        Singer singer = mv.getSinger();

        holder.perPic.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(singer.getPerPic(),context)));
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(mv.getImg(),context),item,height));
        holder.detail.setText(mv.getDetail());
        holder.time.setText(mv.getTime());
        holder.playNumber.setText(mv.getPlayNumber()+"");
        holder.singerName.setText(singer.getSingerName());
        holder.mvSingerName.setText(singer.getSingerName());
    }

    @Override
    public int getItemCount() {
        return mvList.size();
    }

    class MvViewHolder extends RecyclerView.ViewHolder{

        ImageView perPic,img;
        TextView singerName,detail,mvName,mvSingerName,playNumber,time;

        public MvViewHolder(@NonNull View itemView) {
            super(itemView);
            perPic = itemView.findViewById(R.id.per_pic);
            img = itemView.findViewById(R.id.img);
            singerName = itemView.findViewById(R.id.singer_name);
            detail = itemView.findViewById(R.id.detail);
            mvName = itemView.findViewById(R.id.mv_name);
            mvSingerName = itemView.findViewById(R.id.mv_singer_name);
            playNumber = itemView.findViewById(R.id.play_number);
            time = itemView.findViewById(R.id.time);
        }
    }
}
