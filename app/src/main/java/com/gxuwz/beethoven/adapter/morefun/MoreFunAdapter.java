package com.gxuwz.beethoven.adapter.morefun;

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
import com.gxuwz.beethoven.model.entity.MoreFun;
import com.gxuwz.beethoven.model.entity.current.SysUser;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;
import com.gxuwz.beethoven.viewholder.morefun.MoreFunViewHolder;

import java.util.List;

public class MoreFunAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<MoreFun> moreFunList;

    public MoreFunAdapter(Context context, List<MoreFun> moreFunList) {
        this.context = context;
        this.moreFunList = moreFunList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder ViewHolder = null;
        switch (viewType) {
            case 2:{
                ViewHolder = new MoreFunViewHolder(LayoutInflater.from(context).inflate(R.layout.item_morefun,null));
            };break;
            case 1:{
                ViewHolder = new PerMessViewHolder(LayoutInflater.from(context).inflate(R.layout.per_mess_item,null));
            };break;
        }
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MoreFun moreFun = moreFunList.get(position);
        switch (moreFun.getType()) {
            case 2:{
                MoreFunViewHolder MoreFunViewHolder = (MoreFunViewHolder)holder;
                MoreFunViewHolder.title.setText(moreFun.getTitle());
                MoreFunViewHolder.funRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                MoreFunViewHolder.funRv.setAdapter(new FunAdapter(context,moreFun.getFunList()));
            };break;
            case 1:{
                PerMessViewHolder perMessViewHolder = (PerMessViewHolder)holder;
                SysUser sysUser = moreFun.getSysUser();
                if(sysUser!=null) {
                    MergeImage.showGlideImgDb(context,StaticHttp.STATIC_BASEURL+sysUser.getAvatar(),perMessViewHolder.coverPicture,48);
                    perMessViewHolder.nickName.setText(sysUser.getNickName());
                    if("1".equals(sysUser.getSex())) {
                        MergeImage.showGlideImgDb(context,R.drawable.icon_man,perMessViewHolder.sexIv,1);
                    } else {
                        MergeImage.showGlideImgDb(context,R.drawable.icon_woman,perMessViewHolder.sexIv,1);
                    }
                } else {
                    MergeImage.showGlideImgDb(context, StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,perMessViewHolder.coverPicture,48);
                }

            };break;
        }
    }

    @Override
    public int getItemCount() {
        return moreFunList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return moreFunList.get(position).getType();
    }

    class PerMessViewHolder extends RecyclerView.ViewHolder{

        ImageView coverPicture,sexIv;
        TextView nickName,detail;

        public PerMessViewHolder(@NonNull View itemView) {
            super(itemView);
            coverPicture = itemView.findViewById(R.id.cover_picture);
            sexIv = itemView.findViewById(R.id.sex_iv);
            nickName = itemView.findViewById(R.id.nick_name);
            detail = itemView.findViewById(R.id.detail);
        }
    }
}
