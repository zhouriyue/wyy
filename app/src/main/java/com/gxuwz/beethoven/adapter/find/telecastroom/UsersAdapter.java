package com.gxuwz.beethoven.adapter.find.telecastroom;

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

/**
 * 显示前4个粉丝的头像
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    Context context;
    List<SysUser> sysUserList;

    public UsersAdapter(Context context, List<SysUser> sysUserList) {
        this.context = context;
        this.sysUserList = sysUserList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsersViewHolder(LayoutInflater.from(context).inflate(R.layout.user_show_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        SysUser sysUser = sysUserList.get(position);
        holder.perPic.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(sysUser.getPerPic(),context)));
    }

    @Override
    public int getItemCount() {
        return sysUserList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{

        ImageView perPic;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            perPic = itemView.findViewById(R.id.per_pic);
        }
    }
}
