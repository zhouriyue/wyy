package com.gxuwz.beethoven.adapter.find.ittalk;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.find.Banners;
import com.gxuwz.beethoven.model.entity.find.talk.ITTalk;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.youth.banner.Banner;

import java.util.List;

public class ITTalkAdapter extends RecyclerView.Adapter<ITTalkAdapter.ITTalkViewHolder> {

    Context context;
    List<ITTalk> itTalkList;

    public ITTalkAdapter(Context context, List<ITTalk> itTalkList) {
        this.context = context;
        this.itTalkList = itTalkList;
    }

    @NonNull
    @Override
    public ITTalkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ITTalkViewHolder(LayoutInflater.from(context).inflate(R.layout.item_ittalk,null));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ITTalkViewHolder holder, int position) {
        ITTalk itTalk = itTalkList.get(position);
        Banners.initBanner(holder.banner,itTalk.getImages());
        holder.username.setText(itTalk.getSysUser().getUserName());
        holder.title.setText(itTalk.getTitle());
        holder.content.setText(itTalk.getContent());
        holder.perPic.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes("youth",context)));
        holder.comment.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        holder.comment.setAdapter(new CommentAdapter(context,itTalk.getCommentList()));
    }

    @Override
    public int getItemCount() {
        return itTalkList.size();
    }

    class ITTalkViewHolder extends RecyclerView.ViewHolder{

        Banner banner;
        TextView username,title,content;
        ImageView perPic;
        RecyclerView yunVillageRv,comment;

        public ITTalkViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
            username = itemView.findViewById(R.id.username);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            perPic = itemView.findViewById(R.id.per_pic);
            yunVillageRv = itemView.findViewById(R.id.yun_village_rv);
            comment = itemView.findViewById(R.id.comment);
        }
    }
}
