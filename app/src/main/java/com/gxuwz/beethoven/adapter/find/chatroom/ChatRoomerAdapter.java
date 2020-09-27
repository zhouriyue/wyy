package com.gxuwz.beethoven.adapter.find.chatroom;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;

import java.util.List;

public class ChatRoomerAdapter extends RecyclerView.Adapter<ChatRoomerAdapter.ChatRoomerViewHolder> {

    Context context;
    List<SysUser> sysUserList;

    public ChatRoomerAdapter(Context context, List<SysUser> sysUserList) {
        this.context = context;
        this.sysUserList = sysUserList;
    }

    @NonNull
    @Override
    public ChatRoomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatRoomerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_roomer,null));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomerViewHolder holder, int position) {
        SysUser sysUser = sysUserList.get(position);
        if("男".equals(sysUser.getSex())) {
            holder.rcBg.setBackground(context.getResources().getDrawable(R.drawable.shape_man));
        } else {
            holder.rcBg.setBackground(context.getResources().getDrawable(R.drawable.shape_woman));
        }
        holder.roomerImg.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(sysUser.getPerPic(),context)));
    }

    @Override
    public int getItemCount() {
        return sysUserList.size();
    }

    class ChatRoomerViewHolder extends ViewHolder{

        LinearLayout rcBg;
        ImageView roomerImg;

        public ChatRoomerViewHolder(@NonNull View itemView) {
            super(itemView);
            rcBg = itemView.findViewById(R.id.rc_bg);
            roomerImg = itemView.findViewById(R.id.roomer_img);
        }
    }
}
