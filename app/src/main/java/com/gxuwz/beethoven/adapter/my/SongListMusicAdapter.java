package com.gxuwz.beethoven.adapter.my;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.hanlder.MusicHandler;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.SongListsMusic;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.HttpUtil;

import java.util.List;

public class SongListMusicAdapter extends RecyclerView.Adapter<SongListMusicAdapter.SongListViewHolder> {
    private Context context;
    List<SongListsMusic> songListsMusics;
    private int backItem = -1;
    ImageView ptTagBack;
    /**
     * 获取SharedPreferences对象
     */
    SharedPreferences sharedPreferences;
    /**
     * 歌单对象
     */
    SongList songList;

    public SongListMusicAdapter(Context context, List<SongListsMusic> songListsMusics,
                                SharedPreferences sharedPreferences,SongList songList) {
        this.context = context;
        this.songListsMusics = songListsMusics;
        this.sharedPreferences = sharedPreferences;
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongListMusicAdapter.SongListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongListMusicAdapter.SongListViewHolder(LayoutInflater.from(context).inflate(R.layout.song_list_music_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongListMusicAdapter.SongListViewHolder holder, int position) {
        SongListsMusic songListsMusic = songListsMusics.get(position);
        holder.position.setText((position+1)+"");
        holder.musicName.setText(songListsMusic.getMusicName());
        holder.nameAndSinger.setText(songListsMusic.getSingerName()+"-"+songListsMusic.getMusicName()+".mp3");
        if(MusicService.position==position&&MusicService.isRun) {
            if(MusicService.ptTagBack!=null) {
                MusicService.ptTagBack.setBackgroundResource(0);
                MusicService.ptTagBack.setImageBitmap(HttpUtil.getRes("stop1",context));
            }
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 设置播放歌曲的歌名、歌手、歌曲
                 */
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("songName",songListsMusics.get(position).getMusicName());
                editor.putString("singer",songListsMusics.get(position).getSingerName());
                editor.putString("musicUrl",songListsMusics.get(position).get_links().getMusic().getHref());
                editor.putString("songListUrl",songList.getSongListUrl());
                /**
                 * playStatus==1表示播放
                 * playStatus==0表示停止
                 */
                editor.putString("playStatus","1");
                editor.commit();

                MusicHandler musicHandler = new MusicHandler();
                musicHandler.setContext(context);
                if(MusicService.ptTagBack!=null) {
                    MusicService.ptTagBack.setImageBitmap(HttpUtil.getRes("play3",context));
                }
                holder.ptTag.setBackgroundResource(0);
                holder.ptTag.setImageBitmap(HttpUtil.getRes("stop1",context));
                MusicService.ptTagBack = holder.ptTag;
                MusicService.position = position;
                MusicService.isRun = true;
                HttpUtil.get(songListsMusics.get(position).get_links().getMusic().getHref(),musicHandler);

                IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_PLAY,context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songListsMusics.size();
    }

    class SongListViewHolder extends RecyclerView.ViewHolder {

        private TextView position;
        private TextView musicName;
        private TextView nameAndSinger;
        private RelativeLayout relativeLayout;
        private ImageView ptTag;

        public SongListViewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position);
            musicName = itemView.findViewById(R.id.music_name);
            nameAndSinger = itemView.findViewById(R.id.music_singer_name);
            relativeLayout = itemView.findViewById(R.id.id);
            ptTag = itemView.findViewById(R.id.pt_tag);
        }
    }

    public int getBackItem() {
        return backItem;
    }

    public void setBackItem(int backItem) {
        this.backItem = backItem;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
}
