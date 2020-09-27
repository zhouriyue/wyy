package com.gxuwz.beethoven.adapter.find;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class RoomManAdapter extends RecyclerView.Adapter<RoomManAdapter.RoomManViewHolder> {

    Context context;
    List<SysUser> sysUserList;

    public RoomManAdapter(Context context, List<SysUser> sysUserList) {
        this.context = context;
        this.sysUserList = sysUserList;
    }

    @NonNull
    @Override
    public RoomManViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomManViewHolder(LayoutInflater.from(context).inflate(R.layout.item_room_man,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomManViewHolder holder, int position) {
        SysUser sysUser = sysUserList.get(position);
        holder.perPic.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(sysUser.getPerPic(),context)));
    }

    @Override
    public int getItemCount() {
        return sysUserList.size();
    }

    class RoomManViewHolder extends RecyclerView.ViewHolder{

        ImageView perPic;

        public RoomManViewHolder(@NonNull View itemView) {
            super(itemView);
            perPic = itemView.findViewById(R.id.per_pic);
        }
    }
}
