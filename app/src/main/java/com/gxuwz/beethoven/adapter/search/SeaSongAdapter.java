package com.gxuwz.beethoven.adapter.search;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.OperateDao;
import com.gxuwz.beethoven.model.entity.current.Operate;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.my.songlist.SLMore;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownView;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SeaSongAdapter extends RecyclerView.Adapter<SeaSongAdapter.SeaSongViewHolder> {

    LoadDownView loadDownView;
    Context context;
    List<Song> songList;
    OperateDao operateDao;

    public SeaSongAdapter(Context context, List<Song> songList,LoadDownView loadDownView) {
        this.context = context;
        this.songList = songList;
        this.loadDownView = loadDownView;
        this.operateDao = new OperateDao(context);
    }

    @NonNull
    @Override
    public SeaSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SeaSongViewHolder(LayoutInflater.from(context).inflate(R.layout.search_song_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SeaSongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.songName.setText(song.getSongName());
        holder.songName2.setText(song.getSongName());
        initSinger(holder.singerName,song);
        holder.moreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Operate> operates = operateDao.findByType(1);
                loadDownView.initView(operates,v,song);
            }
        });
    }

    /**
     * 获取歌手并显示
     */
    public void initSinger(TextView textView,Song song){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    Gson gson = new Gson();
                    Type listtype = new TypeToken<List<Singer>>(){}.getType();
                    List<Singer> singerList = gson.fromJson(result,listtype);
                    String nameAndSingerName ="";
                    if(singerList!=null&&singerList.size()!=0) {
                        nameAndSingerName += singerList.get(0).getSinName();
                        for (int i = 1;i < singerList.size();i++) {
                            nameAndSingerName += "/"+singerList.get(i).getSinName();
                        }
                    }
                    textView.setText(nameAndSingerName);
                }

            }
        };
        String url = StaticHttp.BASEURL + StaticHttp.SELECT_SINGER;
        url += "?songId="+song.getSongId();
        HttpUtil.get(url,handler);
    }

    public void updateData(List<Song> songs){
        this.songList = songs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    class SeaSongViewHolder extends RecyclerView.ViewHolder{

        TextView songName,singerName,songName2;
        ImageView moreImg;

        public SeaSongViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.song_name);
            singerName = itemView.findViewById(R.id.singer_name);
            songName2 = itemView.findViewById(R.id.song_name2);
            moreImg = itemView.findViewById(R.id.more_img);
        }
    }
}
