package com.gxuwz.beethoven.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.List;

/**
 * Index Activity组件
 * 底部部分信息变化
 */
public class IndexBottomBarReceiver extends BroadcastReceiver {
    /**
     * 0表示暂停，按钮显示为play1
     * 1表示播放，按钮显示为stop
     */
    public static final int FLAT_PLAY = 1;
    public static final int FLAT_STOP = 0;
    public static final String CONTROLLER = "controller";
    /**
     * 广播接收唯一标识
     */
    public static final String ACTION = "INDEXBOTTOMBAR";

    SharedPreferences sharedPreferences;
    /**
     * playSongListRrl：底部头像
     * songName: 歌曲名
     * singer: 歌手
     * playAndStop：播放按钮
     */
    ImageView imgUriIV;
    TextView songNameTV,singerTv;
    ImageView playAndStop;
    SongDao songDao;
    SingerDao singerDao;
    LocalSongDao localSongDao;

    public IndexBottomBarReceiver(ImageView imgUriIV, TextView songNameTV, TextView singer) {
        this.imgUriIV = imgUriIV;
        this.songNameTV = songNameTV;
        this.singerTv = singer;
    }

    public static void sendBroadcast(int controller, Context context){
        Intent indexBottomBar = new Intent(IndexBottomBarReceiver.ACTION);
        indexBottomBar.putExtra(IndexBottomBarReceiver.CONTROLLER,controller);
        context.sendBroadcast(indexBottomBar);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        init(context);
        int controller = intent.getIntExtra(CONTROLLER,-1);
        if(controller==0) {
            MergeImage.showGlideImgDb(context, R.drawable.icon_play1,playAndStop,1);
        } else {
            MergeImage.showGlideImgDb(context, R.drawable.stop_bar,playAndStop,1);
            Long slId = sharedPreferences.getLong("slId",-1);
            Long songId = sharedPreferences.getLong("songId",-1);
            if(slId!=-1) {
                Song song = songDao.findById(songId);
                songNameTV.setText(song.getSongName());
                List<Singer> singerList =  singerDao.findSongSiner(song.getSongId());
                String singerName = "";
                if(singerList!=null&&singerList.size()!=0) {
                    singerName += singerList.get(0).getSinName();
                    for(int i = 1;i < singerList.size();i++) {
                        singerName += "/" + singerList.get(i).getSinName();
                    }
                }
                singerTv.setText(singerName);
                MergeImage.showGlideImgDb(context, StaticHttp.STATIC_BASEURL+song.getCoverPicture(),
                        R.drawable.icon_singer_default,imgUriIV,19);
            } else {
                LocalSong localSong = localSongDao.findBySongId(songId);
                songNameTV.setText(localSong.getSongName());
                singerTv.setText(localSong.getSingerName());
                MergeImage.showGlideImgDb(context, StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,
                        R.drawable.icon_singer_default,imgUriIV,19);
            }
        }
    }

    public void init(Context context){
        if(songDao==null) {
            songDao = new SongDao(context);
        }
        if(singerDao==null) {
            singerDao = new SingerDao(context);
        }
        if(sharedPreferences==null) {
            sharedPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
        }
        if(localSongDao==null) {
            localSongDao = new LocalSongDao(context);
        }
    }

    public ImageView getPlayAndStop() {
        return playAndStop;
    }

    public void setPlayAndStop(ImageView playAndStop) {
        this.playAndStop = playAndStop;
    }
}
