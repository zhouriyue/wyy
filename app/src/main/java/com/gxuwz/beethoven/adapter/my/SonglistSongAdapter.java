package com.gxuwz.beethoven.adapter.my;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.LatePlayDao;
import com.gxuwz.beethoven.dao.OperateDao;
import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.model.entity.LatePlay;
import com.gxuwz.beethoven.model.entity.current.Operate;
import com.gxuwz.beethoven.model.entity.current.PlayList;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.lang.reflect.Type;
import java.util.List;

public class SonglistSongAdapter extends RecyclerView.Adapter<SonglistSongAdapter.SonglistSongViewHolder> {

    Context context;
    List<Song> songList;
    LoadDownView loadDownView;
    PlayListDao playListDao;
    SingerDao singerDao;
    LatePlayDao latePlayDao;
    OperateDao operateDao;
    SharedPreferences sharedPreferences;
    Long slId;

    public SonglistSongAdapter(Context context, List<Song> songList,Long slId) {
        this.context = context;
        this.songList = songList;
        playListDao = new PlayListDao(context);
        singerDao = new SingerDao(context);
        latePlayDao = new LatePlayDao(context);
        this.slId = slId;
        this.sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        this.operateDao = new OperateDao(context);
    }

    public SonglistSongAdapter(Context context, List<Song> songList, Long slId, LoadDownView loadDownView) {
        this.context = context;
        this.songList = songList;
        playListDao = new PlayListDao(context);
        singerDao = new SingerDao(context);
        latePlayDao = new LatePlayDao(context);
        this.operateDao = new OperateDao(context);
        this.slId = slId;
        this.sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        this.loadDownView = loadDownView;
    }

    @NonNull
    @Override
    public SonglistSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SonglistSongViewHolder(LayoutInflater.from(context).inflate(R.layout.song_list_music_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SonglistSongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.position.setText((position+1)+"");
        holder.songName.setText(song.getSongName());
        switch (song.getTimbreType()){
            case 1:{
                holder.timbreTypeTv.setText("标准");
            };break;
            case 2:{
                holder.timbreTypeTv.setText("HQ");
            };break;
            case 3:{
                holder.timbreTypeTv.setText("SQ");
            };break;
        }
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    Gson gson = new Gson();
                    Type listtype = new TypeToken<List<Singer>>(){}.getType();
                    List<Singer> singerList = gson.fromJson(result,listtype);
                    String nameAndSingerName = song.getSongName();
                    if(singerList!=null&&singerList.size()!=0) {
                        nameAndSingerName += "-"+singerList.get(0).getSinName();
                        for (int i = 1;i < singerList.size();i++) {
                            nameAndSingerName += "/"+singerList.get(i).getSinName();
                        }
                    }
                    for(int i = 0;i < singerList.size();i++) {
                        if(singerDao.findById(singerList.get(i).getSinId())==null) {
                            singerDao.insert(singerList.get(i));
                        }
                        if(singerDao.findById(singerList.get(i).getSinId())!=null&&
                                !singerDao.isSongToSinger(song.getSongId(),singerList.get(i).getSinId())) {
                            singerDao.insertSongToSinger(song.getSongId(),singerList.get(i).getSinId());
                        }
                    }
                    holder.songAndsingerName.setText(nameAndSingerName);
                    holder.ptTag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PlayList playList = new PlayList();
                            playList.setSongId(song.getSongId());
                            playList.setSlId(slId);
                            if(!playListDao.isExist(playList)) {
                                playListDao.insert(playList);
                            }
                            LatePlay latePlay = latePlayDao.findLatePlay(playList.getSlId(),playList.getSongId());
                            if(latePlay==null) {
                                latePlayDao.insert(playList.getSlId(),playList.getSongId());
                            } else {
                                latePlayDao.update(latePlay);
                            }
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putLong("slId",slId);
                            editor.putLong("songId",song.getSongId());
                            editor.commit();
                            IndexBottomBarReceiver.sendBroadcast(1,context);
                            MusicService.musicCtrl(context,2);
                        }
                    });
                }
            }
        };
        String url = StaticHttp.BASEURL + StaticHttp.SELECT_SINGER;
        url += "?songId="+song.getSongId();
        HttpUtils.get(url,handler);
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Operate> operates = operateDao.findByType(2);
                loadDownView.initView(operates,v,slId,song);
            }
        });
    }

    public void updateData(List<Song> songList) {
        this.songList = songList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    class SonglistSongViewHolder extends RecyclerView.ViewHolder{

        LinearLayout pId;
        ImageView ptTag,more;
        TextView position,songName,songAndsingerName,timbreTypeTv;

        public SonglistSongViewHolder(@NonNull View itemView) {
            super(itemView);
            pId = itemView.findViewById(R.id.p_id);
            ptTag = itemView.findViewById(R.id.pt_tag);
            position = itemView.findViewById(R.id.position);
            songName = itemView.findViewById(R.id.music_name);
            songAndsingerName = itemView.findViewById(R.id.music_singer_name);
            more = itemView.findViewById(R.id.more);
            timbreTypeTv = itemView.findViewById(R.id.timbreType_tv);
        }
    }
}
