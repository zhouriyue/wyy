package com.gxuwz.beethoven.receiver.playview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.page.fragment.playview.LyricShowView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.util.BlurUtil;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.List;

public class PlayViewReceiver extends BroadcastReceiver {

    public final static String ACTION = "playview";

    SharedPreferences sharedPreferences;
    TextView songNameTV,singerNameTv;
    ImageView playingPlay;
    LyricShowView lyricShowView;
    ImageView bg;
    ImageView disc;

    SongDao songDao;
    SingerDao singerDao;
    LocalSongDao localSongDao;

    public PlayViewReceiver(TextView songName, TextView singerName, ImageView playingPlay,ImageView bg,ImageView disc,LyricShowView lyricShowView) {
        this.songNameTV = songName;
        this.singerNameTv = singerName;
        this.playingPlay = playingPlay;
        this.lyricShowView = lyricShowView;
        this.bg = bg;
        this.disc = disc;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        initData(context);
        Long slId = sharedPreferences.getLong("slId",-1);
        Long songId = sharedPreferences.getLong("songId",-1);
        if(slId!=-1) {
            Song song = songDao.findById(songId);
            songNameTV.setText(song.getSongName());
            String singers = "";
            List<Singer> singerList = singerDao.findSongSiner(songId);
            if(singerList!=null&&singerList.size()!=0) {
                singers += singerList.get(0).getSinName();
                for(int i = 0;i < singerList.size();i++) {
                    singers += singerList.get(i).getSinName();
                }
            }
            singerNameTv.setText(singers);
        } else {
            LocalSong localSong = localSongDao.findBySongId(songId);
            songNameTV.setText(localSong.getSongName());
            singerNameTv.setText(localSong.getSingerName());
        }
        MergeImage.showGlideImgDb(context, R.drawable.icon_stop_playview,playingPlay,1);
        lyricShowView.initEvents();
        messShow(context);
    }

    private void initData(Context context){
        if(sharedPreferences==null) {
            sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        }
        if(songDao==null) {
            songDao = new SongDao(context);
        }
        if(singerDao==null) {
            singerDao = new SingerDao(context);
        }
        if(localSongDao==null) {
            localSongDao = new LocalSongDao(context);
        }
    }

    public void messShow(Context context){
        Long songId = sharedPreferences.getLong("songId",-1);
        Song song = songDao.findById(songId);
        if(song!=null) {
            String url = StaticHttp.STATIC_BASEURL+song.getCoverPicture();
            Glide.with(context)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)//缓存
                    .load(url)
                    .centerCrop()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            bg.setImageBitmap(BlurUtil.doBlur(resource, 10, 30));
                            disc.setImageBitmap(MergeImage.mergeThumbnailBitmap(HttpUtil.getRes("dibian", context), resource));
                        }
                    });
        }
    }

    /**
     * 发生广播
     * @param context
     */
    public static void sendBroadcast(Context context){
        Intent indexBottomBar = new Intent(PlayViewReceiver.ACTION);
        context.sendBroadcast(indexBottomBar);
    }
}
