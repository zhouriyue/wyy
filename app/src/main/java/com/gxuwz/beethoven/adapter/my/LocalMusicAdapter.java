package com.gxuwz.beethoven.adapter.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.model.entity.Music;
import com.gxuwz.beethoven.model.entity.PlayList;
import com.gxuwz.beethoven.page.index.playlistview.CurrentPlayView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.Player;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicAdapter extends RecyclerView.Adapter<LocalMusicAdapter.LocalMusicViewHolder> {
    private Context context;
    SharedPreferences sharedPreferences;
    Intent intent;
    List<Music> musicList;
    PlayList playList;
    private int backItem = -1;
    ImageView ptTagBack;

    public LocalMusicAdapter(Context context, List<Music> musicList,SharedPreferences sharedPreferences) {
        this.context = context;
        this.musicList = musicList;
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public LocalMusicAdapter.LocalMusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocalMusicAdapter.LocalMusicViewHolder(LayoutInflater.from(context).inflate(R.layout.local_music_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocalMusicAdapter.LocalMusicViewHolder holder, int position) {
        Music music = musicList.get(position);
        holder.position.setText((position+1)+"");
        holder.musicName.setText(music.getMusicName());
        holder.nameAndSinger.setText(music.getSinger()+"-"+music.getMusicName());
        if(MusicService.position==position&&MusicService.isRun) {
            if(MusicService.ptTagBack!=null) {
                MusicService.ptTagBack.setBackgroundResource(0);
                MusicService.ptTagBack.setImageBitmap(HttpUtil.getRes("stop1",context));
            }
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MusicService.ptTagBack!=null) {
                    MusicService.ptTagBack.setImageBitmap(HttpUtil.getRes("play3",context));
                }
                holder.ptTag.setBackgroundResource(0);
                holder.ptTag.setImageBitmap(HttpUtil.getRes("stop1",context));
                saveSharedPreferences(music);
                Player.playCurrent(context);
                MusicService.ptTagBack = holder.ptTag;
                MusicService.position = position;
                MusicService.isRun = true;
                IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_PLAY,context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    class LocalMusicViewHolder extends RecyclerView.ViewHolder {

        private TextView position;
        private TextView musicName;
        private TextView nameAndSinger;
        private RelativeLayout relativeLayout;
        private ImageView ptTag;

        public LocalMusicViewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position);
            musicName = itemView.findViewById(R.id.music_name);
            nameAndSinger = itemView.findViewById(R.id.music_singer_name);
            relativeLayout = itemView.findViewById(R.id.id);
            ptTag = itemView.findViewById(R.id.pt_tag);
        }
    }

    private void saveSharedPreferences(Music music){
        PlayList playList = saveSong(music);
        /**
         * 设置播放歌曲的歌名、歌手、歌曲
         */
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id",playList.getId());
        editor.putString("songName",playList.getSongName());
        editor.putString("singerName",playList.getSingerName());
        editor.putInt("songTime",playList.getSongTime());
        editor.putString("localUri",playList.getLocalUri());
        editor.putString("networkUri",playList.getNetworkUri());
        editor.putString("songListUri",playList.getSongListUri());
        /**
         * playStatus==1表示播放
         * playStatus==0表示停止
         */
        editor.putString("playStatus","1");
        editor.commit();
    }

    private PlayList saveSong(Music music){
        List<PlayList> playLists = new ArrayList<PlayList>();
        PlayListDao playListDao = new PlayListDao(context);
        playList = new PlayList();
        playList.setSongName(music.getMusicName());
        playList.setSingerName(music.getSinger());
        playList.setSongTime(music.getDuration());
        playList.setLocalUri(music.getSongUrl());
        playLists = playListDao.find(playList);
        int size = playLists.size();
        if(size==0) {
            playListDao.insert(playList);
            playList = playListDao.find(playList).get(0);
            freshPlayList(playList);
        } else {
            playList = playLists.get(0);
        }
        return playList;
    }

    private void sendBroadcast(int position){
        intent = new Intent("CTL_ACTION");
        intent.putExtra("controller",2);
        intent.putExtra("playList",playList);
        intent.putExtra("position",position);
    }

    private void freshPlayList(PlayList playList) {
        intent = new Intent(CurrentPlayView.FreshPlayListReceiver.ACTION);
        intent.putExtra("playList",playList);
        context.sendBroadcast(intent);
    }

    public int getBackItem() {
        return backItem;
    }

    public void setBackItem(int backItem) {
        this.backItem = backItem;
    }
}
