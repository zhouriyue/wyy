package com.gxuwz.beethoven.adapter.my;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.model.entity.musicapp.MusicApp;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.WindowPixels;

import java.util.List;

public class MusicAppAdapter extends BaseAdapter {

    Context context;
    List<MusicApp> musicAppList;
    WindowPixels windowPixels;

    public MusicAppAdapter(Context context, List<MusicApp> musicAppList,WindowManager windowManager) {
        this.context = context;
        this.musicAppList = musicAppList;
        windowPixels = new WindowPixels(windowManager);
    }

    @Override
    public int getCount() {
        return musicAppList.size();
    }

    @Override
    public Object getItem(int i) {
        return musicAppList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.music_app_item, null);
        ImageView diagonal,icon,typeIcon;
        TextView title,type;
        LinearLayout typeColor;
        diagonal = view.findViewById(R.id.diagonal);
        icon = view.findViewById(R.id.icon);
        typeIcon = view.findViewById(R.id.type_icon);
        title = view.findViewById(R.id.title);
        type = view.findViewById(R.id.type);
        typeColor = view.findViewById(R.id.type_color);

        MusicApp musicApp = musicAppList.get(i);
        if(musicApp.getDiagonal()!=null) {
            diagonal.setImageBitmap(MergeImage.roundedCustom(HttpUtil.getRes(musicApp.getDiagonal(),context),(int)(100*windowPixels.getDensity()),(int)(125*windowPixels.getDensity())));
        }
        if(musicApp.getTypeIcon()!=null) {
            icon.setImageBitmap(HttpUtil.getRes(musicApp.getIcon(),context));
        }
        if(musicApp.getIcon()!=null) {
            typeIcon.setImageBitmap(HttpUtil.getRes(musicApp.getTypeIcon(),context));
        }
        if(musicApp.getTitle()!=null) {
            title.setText(musicApp.getTitle());
        }
        if(musicApp.getType()!=null) {
            type.setText(musicApp.getType());
        }
        if(musicApp.getTypeColor()!=null) {
            typeColor.setBackground(context.getResources().getDrawable(R.drawable.shape_translu));
        }
        return view;
    }
}
