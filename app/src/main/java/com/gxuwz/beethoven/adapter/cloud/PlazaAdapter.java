package com.gxuwz.beethoven.adapter.cloud;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.mlog.ImageWordMlog;
import com.gxuwz.beethoven.model.entity.mlog.Mlog;
import com.gxuwz.beethoven.model.entity.mlog.VideoMlog;
import com.gxuwz.beethoven.page.index.cloudview.videotalk.VideoTalkActivity;
import com.gxuwz.beethoven.page.index.findview.imgtexttalk.ITTalkActivity;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class PlazaAdapter extends RecyclerView.Adapter<PlazaAdapter.PlazaHolder>{

    private Context context;
    private List<Mlog> mlogList;
    private WindowManager windowManager;

    ImageWordMlog imageWordMlog;
    VideoMlog videoMlog;
    WindowPixels windowPixels;

    int itemWidth,itmeHeight;

    public PlazaAdapter(Context context, List<Mlog> mlogList,WindowManager windowManager) {
        this.context = context;
        this.mlogList = mlogList;
        this.windowManager = windowManager;
        windowPixels = new WindowPixels(windowManager);
        int screen[] = windowPixels.getScreen();
        itemWidth = (int) (((screen[0]-30)/2)*windowPixels.getDensity());
        itmeHeight = (int) (280*windowPixels.getDensity());
    }

    @NonNull
    @Override
    public PlazaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlazaHolder(LayoutInflater.from(context).inflate(R.layout.plaza_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlazaHolder holder, int position) {
        SysUser sysUser;
        if(mlogList.get(position).getType().equals("ImageWordMlog")) {
            imageWordMlog = (ImageWordMlog) mlogList.get(position);
            if(position%2==0) {
                holder.relativeLayout.setMinimumHeight((int) (350*windowPixels.getDensity()));
                holder.diagonal.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(imageWordMlog.getMusicDiagonal(),context),itemWidth, (int) (itmeHeight)));
            } else {
                holder.relativeLayout.setMinimumHeight((int) (350*windowPixels.getDensity()*0.7));
                holder.diagonal.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(imageWordMlog.getMusicDiagonal(),context),itemWidth, (int) (itmeHeight*0.7)));
            }
            holder.content.setText(imageWordMlog.getContent());
            sysUser = imageWordMlog.getSysUser();
            holder.likeNumber.setText(imageWordMlog.getLikeNumber()+"");
            holder.plazaLin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ITTalkActivity.class);
                    context.startActivity(intent);
                }
            });
        } else {
            videoMlog = (VideoMlog) mlogList.get(position);
            holder.relativeLayout.setMinimumHeight((int) (300*windowPixels.getDensity()));
            holder.diagonal.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(videoMlog.getDiagonal(),context),itemWidth,itmeHeight));
            holder.content.setText(videoMlog.getContent());
            sysUser = videoMlog.getSysUser();
            holder.likeNumber.setText(videoMlog.getLikeNumber()+"");
            holder.plazaLin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, VideoTalkActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        holder.perPic.setImageBitmap(MergeImage.circleShow(HttpUtil.getRes(sysUser.getPerPic(),context)));
        holder.username.setText(sysUser.getUserName());
    }

    @Override
    public int getItemCount() {
        return mlogList.size();
    }

    class PlazaHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        LinearLayout plazaLin;
        ImageView diagonal,perPic;
        TextView content,username,likeNumber;

        public PlazaHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.plaza_item_bg);
            plazaLin = itemView.findViewById(R.id.plaza_lin);
            relativeLayout.setMinimumWidth(itemWidth);
            diagonal = itemView.findViewById(R.id.diagonal_mlog);
            content = itemView.findViewById(R.id.content_mlog);
            perPic = itemView.findViewById(R.id.per_pic);
            username = itemView.findViewById(R.id.username);
            likeNumber = itemView.findViewById(R.id.like_number);
        }
    }
}
