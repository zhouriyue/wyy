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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.songlist.SDLPopWinAdapter;
import com.gxuwz.beethoven.dao.LatePlayDao;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.OperateDao;
import com.gxuwz.beethoven.dao.PlayListDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.dao.SongListDao;
import com.gxuwz.beethoven.model.entity.Music;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Operate;
import com.gxuwz.beethoven.model.entity.current.PlayList;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.page.fragment.my.ring.RingTestPW;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownPopuWindow;
import com.gxuwz.beethoven.page.fragment.search.SaveToSonglistPw;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicAdapter extends RecyclerView.Adapter<LocalMusicAdapter.LocalMusicViewHolder> {
    private Context context;
    SharedPreferences sharedPreferences;
    LoadDownPopuWindow loadDownPopuWindow;
    SaveToSonglistPw saveToSonglistPw;
    PlayListDao playListDao;
    LocalSongDao localSongDao;
    SongListDao songListDao;
    SongDao songDao;
    OperateDao operateDao;
    LatePlayDao latePlayDao;
    List<LocalSong> localSongs;
    PlayList playList;
    private int backItem = -1;

    public LocalMusicAdapter(Context context, List<LocalSong> localSongs, SharedPreferences sharedPreferences, LoadDownPopuWindow loadDownPopuWindow) {
        this.context = context;
        this.localSongs = localSongs;
        this.sharedPreferences = sharedPreferences;
        playListDao = new PlayListDao(context);
        localSongDao = new LocalSongDao(context);
        latePlayDao = new LatePlayDao(context);
        songListDao = new SongListDao(context);
        songDao = new SongDao(context);
        operateDao = new OperateDao(context);
        this.loadDownPopuWindow = loadDownPopuWindow;
        this.saveToSonglistPw = new SaveToSonglistPw(context);
    }

    @NonNull
    @Override
    public LocalMusicAdapter.LocalMusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocalMusicAdapter.LocalMusicViewHolder(LayoutInflater.from(context).inflate(R.layout.local_music_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocalMusicAdapter.LocalMusicViewHolder holder, int position) {
        LocalSong localSong = localSongs.get(position);
        holder.position.setText((position+1)+"");
        holder.musicName.setText(localSong.getSongName());
        holder.nameAndSinger.setText(localSong.getSingerName()+"-"+localSong.getSongName());
        holder.ptTag.setOnClickListener(new ItemListenner(holder,localSong,position));
        holder.more.setOnClickListener(new MoreListener(localSong));
    }

    @Override
    public int getItemCount() {
        return localSongs.size();
    }

    class LocalMusicViewHolder extends RecyclerView.ViewHolder {

        private TextView position;
        private TextView musicName;
        private TextView nameAndSinger;
        private ImageView ptTag,more;

        public LocalMusicViewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position);
            musicName = itemView.findViewById(R.id.music_name);
            nameAndSinger = itemView.findViewById(R.id.music_singer_name);
            ptTag = itemView.findViewById(R.id.pt_tag);
            more = itemView.findViewById(R.id.more);
        }
    }

    class MoreListener implements View.OnClickListener{

        LocalSong localSong;

        public MoreListener(LocalSong localSong) {
            this.localSong = localSong;
        }

        @Override
        public void onClick(View v) {
            List<Operate> operates = operateDao.findByType(2);
            initView(operates,v,localSong);
        }
    }

    public void initView(List<Operate> operates, View view, LocalSong localSong){
        View singListView = loadDownPopuWindow.getSingListView();
        TextView songName = singListView.findViewById(R.id.song_name);
        ImageView img = singListView.findViewById(R.id.img);
        if(localSong.getCoverPicture()!=null) {
            String url = StaticHttp.STATIC_BASEURL+localSong.getCoverPicture();
            MergeImage.showGlideImg(context,url,img);
        } else {
            MergeImage.showGlideImgDb(context,StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,img,1);
        }
        songName.setText(localSong.getSongName());
        RecyclerView slMoreRv = singListView.findViewById(R.id.sl_more_rv);
        //initSinger(singerName);
        if(localSong!=null) {
            slMoreRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            SDLPopWinAdapter sdlPopWinAdapter = new SDLPopWinAdapter(context,operates,saveToSonglistPw,view,localSong);
            sdlPopWinAdapter.setLdPopupWindow(loadDownPopuWindow.popupWindow);
            slMoreRv.setAdapter(sdlPopWinAdapter);
        }
        loadDownPopuWindow.showAtLocation(view);
    }

    class ItemListenner implements View.OnClickListener {

        LocalMusicViewHolder holder;
        LocalSong localSong;
        int position;

        public ItemListenner(LocalMusicViewHolder holder, LocalSong localSong, int position) {
            this.holder = holder;
            this.localSong = localSong;
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if(MusicService.ptTagBack!=null) {
                MusicService.ptTagBack.setImageBitmap(HttpUtil.getRes("play3",context));
            }
            holder.ptTag.setBackgroundResource(0);
            holder.ptTag.setImageBitmap(HttpUtil.getRes("stop1",context));
            if(localSong==null) {
                localSong = new LocalSong();
                localSong.setSongName(localSong.getSongName());
                localSong.setSongUrl(localSong.getSongUrl());
                localSong.setSingerName(localSong.getSingerName());
                localSongDao.insert(localSong);
                localSong = localSongDao.findByUrl(localSong.getSongUrl());
            } else {
                if(!songDao.isSlToSong(-1,localSong.getSongId())){
                    songDao.insertSlToSong(-1,localSong.getSongId());
                }
                localSong = localSongDao.findByUrl(localSong.getSongUrl());
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong("slId",-1);
            editor.putLong("songId",localSong.getSongId());
            editor.commit();
            playList = new PlayList();
            playList.setSlId((long) -1);
            playList.setSongId(localSong.getSongId());
            if(!playListDao.isExist(playList)){
                playListDao.insert(playList);
            }
            MusicService.musicCtrl(context,2);
            IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_PLAY,context);
        }
    }

    public int getBackItem() {
        return backItem;
    }

    public void setBackItem(int backItem) {
        this.backItem = backItem;
    }
}
