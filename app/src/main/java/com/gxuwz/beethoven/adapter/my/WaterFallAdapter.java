package com.gxuwz.beethoven.adapter.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.SongListDao;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.SysUser;

import java.util.List;

public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.WaterFallViewHolder>{
    private Context context;
    List<SongList> songLists;
    SysUser sysUser = null;
    private boolean isCache = false;
    SongListDao songListDao = null;

    public WaterFallAdapter(Context context,List<SongList> songLists,SysUser sysUser) {
        this.context = context;
        this.songLists = songLists;
        this.sysUser = sysUser;
        this.songListDao = new SongListDao(context);
    }

    public WaterFallAdapter(Context context, List<SongList> songLists, SysUser sysUser, boolean isCache) {
        this.context = context;
        this.songLists = songLists;
        this.sysUser = sysUser;
        this.isCache = isCache;
    }

    @NonNull
    @Override
    public WaterFallAdapter.WaterFallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WaterFallViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_water_fall_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WaterFallAdapter.WaterFallViewHolder holder, int position) {
        /*SongList songList = songLists.get(position);
        if (isCache){
            Glide.with(context).load(MergeImage.roundedCustomDB(HttpUtils.getLocalImage(songList.getSongListUrl()),60,60,5)).into(holder.iv);
        } else {
            final Handler perPicViewHandle = new Handler() {
                public void handleMessage(android.os.Message msg) {
                    String url = (String) msg.obj;
                    songList.setUserId(sysUser.getUserId());
                    songList.setSongListId(songList.getReallyId());
                    songList.setSongListMusic(songList.get_links().getSongListMusics().getHref()+"");
                    songList.setSongListUrl(url);
                    if(!songListDao.isExsit(songList.getReallyId())){
                        songListDao.insert(songList);
                    } else {
                        songListDao.update(songList);
                    }
                    Glide.with(context).load(MergeImage.roundedCustomDB(HttpUtils.getLocalImage(songList.getSongListUrl()),60,60,5)).into(holder.iv);
                };
            };
            new Thread(){
                @Override
                public void run() {
                    Bitmap imageDate = HttpUtils.getImage(songList.getSongListUrl());
                    String url = HttpUtils.saveBitmap(context,imageDate,HttpUtils.USER,HttpUtils.getFileName(songList.getSongListUrl()));
                    Message msg = new Message();
                    msg.obj = url;
                    perPicViewHandle.sendMessage(msg);
                }
            }.start();
        }
        holder.songListName.setText(songList.getSongListName());
        holder.songListNumber.setText(""+songList.getSongNumber().intValue()+"首");
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, SongListActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("songList", songList);
                b.putSerializable("sysUser",sysUser);
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return songLists.size();
    }

    class WaterFallViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView songListName;
        private TextView songListNumber;
        public WaterFallViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.song_list_url);
            songListName = itemView.findViewById(R.id.song_list_name);
            songListNumber = itemView.findViewById(R.id.song_list_number);
        }
    }

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }
}