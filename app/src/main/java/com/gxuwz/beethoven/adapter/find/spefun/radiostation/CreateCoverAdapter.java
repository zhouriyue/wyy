package com.gxuwz.beethoven.adapter.find.spefun.radiostation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.find.spefun.radiostation.RadioStation;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class CreateCoverAdapter extends RecyclerView.Adapter<CreateCoverAdapter.CreateCoverViewHolder> {

    Context context;
    List<RadioStation> radioStationList;

    public CreateCoverAdapter(Context context, List<RadioStation> radioStationList) {
        this.context = context;
        this.radioStationList = radioStationList;
    }

    @NonNull
    @Override
    public CreateCoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CreateCoverViewHolder(LayoutInflater.from(context).inflate(R.layout.item_srs_createcover,null));
    }

    @Override
    public void onBindViewHolder(@NonNull CreateCoverViewHolder holder, int position) {
        RadioStation radioStation = radioStationList.get(position);
        holder.img.setImageBitmap(MergeImage.roundedCustomDB(HttpUtil.getRes(radioStation.getImg(),context),60,60,5));
        holder.hotNumber.setText(radioStation.getHotNumber()+"");
        SysUser sysUser = radioStation.getSysUser();
        holder.perPic.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(sysUser.getPerPic(),context)));
        holder.title.setText(radioStation.getTitle());
        holder.username.setText(sysUser.getUserName());
    }

    @Override
    public int getItemCount() {
        return radioStationList.size();
    }

    class CreateCoverViewHolder extends RecyclerView.ViewHolder{

        ImageView img,perPic;
        TextView title,username,hotNumber;

        public CreateCoverViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            perPic = itemView.findViewById(R.id.per_pic);
            title = itemView.findViewById(R.id.title);
            username = itemView.findViewById(R.id.username);
            hotNumber = itemView.findViewById(R.id.hot_number);
        }
    }
}
