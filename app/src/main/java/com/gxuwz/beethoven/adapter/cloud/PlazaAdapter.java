package com.gxuwz.beethoven.adapter.cloud;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.mlog.ImageWordMlog;
import com.gxuwz.beethoven.model.entity.mlog.Mlog;
import com.gxuwz.beethoven.model.entity.mlog.VideoMlog;
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
        itmeHeight = (int) (230*windowPixels.getDensity());
    }

    @NonNull
    @Override
    public PlazaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlazaHolder(LayoutInflater.from(context).inflate(R.layout.activity_cloud_plaza_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlazaHolder holder, int position) {
        if(mlogList.get(position).getType().equals("ImageWordMlog")) {

            imageWordMlog = (ImageWordMlog) mlogList.get(position);
            if(position/2==0) {
                holder.relativeLayout.setMinimumHeight((int) (300*windowPixels.getDensity()));
                holder.diagonal.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(imageWordMlog.getMusicDiagonal(),context),itemWidth, (int) (itmeHeight)));
            } else {
                holder.relativeLayout.setMinimumHeight((int) (300*windowPixels.getDensity()*0.8));
                holder.diagonal.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(imageWordMlog.getMusicDiagonal(),context),itemWidth, (int) (itmeHeight*0.8)));
            }
            holder.content.setText(imageWordMlog.getContent());
        } else {
            videoMlog = (VideoMlog) mlogList.get(position);
            holder.relativeLayout.setMinimumHeight((int) (300*windowPixels.getDensity()));
            holder.diagonal.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(videoMlog.getDiagonal(),context),itemWidth,itmeHeight));
            holder.content.setText(videoMlog.getContent());
        }

    }

    @Override
    public int getItemCount() {
        return mlogList.size();
    }

    class PlazaHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        ImageView diagonal;
        TextView content;

        public PlazaHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.plaza_item_bg);
            relativeLayout.setMinimumWidth(itemWidth);
            diagonal = itemView.findViewById(R.id.diagonal_mlog);
            content = itemView.findViewById(R.id.content_mlog);
        }
    }
}
