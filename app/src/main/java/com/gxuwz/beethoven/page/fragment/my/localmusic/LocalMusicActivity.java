package com.gxuwz.beethoven.page.fragment.my.localmusic;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.page.fragment.playview.ActivityPlayView;
import com.gxuwz.beethoven.page.fragment.playlistview.PlayListView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.MusicUtils;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.List;

/**
 * 本地音乐
 */
public class LocalMusicActivity extends LocalMusicBase{

    /**
     * 底部播放信息栏
     */
    IndexBottomBarReceiver indexBottomBarReceiver;
    SongDao songDao;
    SingerDao singerDao;
    LocalSongDao localSongDao;
    ImageView playSonglistUrl,playAndStop,playListIv;
    TextView songNameTv,singerTv;
    PlayListView playListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.local_music);
        /**
         * 隐藏标题栏
         * hide title box
         */
        getSupportActionBar().hide();
        init();
        initData();
        /**
         * 初始化右上角弹框
         */
        new RightTopPopupWindowInit(getWindowManager(),layoutInflater).init(toMany);

    }

    public void initBottomMess(){
        playSonglistUrl = findViewById(R.id.play_song_list_url);
        songNameTv = findViewById(R.id.song_name);
        singerTv = findViewById(R.id.singer);
        playAndStop = findViewById(R.id.play_and_stop);
        playListIv = findViewById(R.id.play_list);
        Long slId = sharedPreferences.getLong("slId",-1);
        Long songId = sharedPreferences.getLong("songId", -1);
        if(slId!=-1) {
            Song song = songDao.findById(songId);
            if(song!=null) {
                songNameTv.setText(song.getSongName());
                List<Singer> singerList = singerDao.findSongSiner(song.getSongId());
                String singer = "";
                if(singerList!=null&&singerList.size()!=0) {
                    singer += singerList.get(0).getSinName();
                    for(int i = 1;i < singerList.size();i++) {
                        singer += "/"+singerList.get(i).getSinName();
                    }
                }
                singerTv.setText(singer);
                if (song.getCoverPicture() != null) {
                    MergeImage.showGlideImgDb(this, StaticHttp.STATIC_BASEURL+song.getCoverPicture(),playSonglistUrl,19);
                } else {
                    MergeImage.showGlideImgDb(this, R.drawable.icon_singer_default,playSonglistUrl,19);
                }
            }
        } else {
            LocalSong localSong = localSongDao.findBySongId(songId);
            if(localSong!=null) {
                songNameTv.setText(localSong.getSongName());
                singerTv.setText(localSong.getSingerName());
            }
            MergeImage.showGlideImgDb(this, StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,playSonglistUrl,19);
        }
        /**
         * 注册底部广播接收者
         * 用于及时改变底部的变化
         */
        playAndStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 底部
                 * 播放或停止音乐
                 */
                Intent intent = new Intent();
                intent.setAction(MusicService.ACTION);
                Intent indexBottomBar = new Intent();
                indexBottomBar.setAction(IndexBottomBarReceiver.ACTION);
                if (Player.isPlayer) {
                    intent.putExtra(MusicService.CONTROLLER, MusicService.STOP);
                    indexBottomBar.putExtra(IndexBottomBarReceiver.CONTROLLER, IndexBottomBarReceiver.FLAT_STOP);
                } else {
                    intent.putExtra(MusicService.CONTROLLER, MusicService.PLAY);
                    indexBottomBar.putExtra(IndexBottomBarReceiver.CONTROLLER, IndexBottomBarReceiver.FLAT_PLAY);
                }
                sendBroadcast(intent);
                sendBroadcast(indexBottomBar);
            }
        });
        if (Player.isPlayer) {
            MergeImage.showGlideImgDb(this,R.drawable.stop_bar,playAndStop,1);
        } else {
            MergeImage.showGlideImgDb(this,R.drawable.icon_play1,playAndStop,1);
        }
        indexBottomBarReceiver = new IndexBottomBarReceiver(playSonglistUrl, songNameTv, singerTv);
        indexBottomBarReceiver.setPlayAndStop(playAndStop);
        IntentFilter indexBottomBar = new IntentFilter(IndexBottomBarReceiver.ACTION);
        registerReceiver(indexBottomBarReceiver, indexBottomBar);
        playSonglistUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocalMusicActivity.this, ActivityPlayView.class);
                startActivity(intent);
            }
        });
        //播放列表
        playListView = new PlayListView(this,playListIv);
    }

    public void initData(){
        MusicUtils.getSDCardRWPermission(LocalMusicActivity.this);
        if(songDao==null) {
            songDao = new SongDao(this);
        }
        if(singerDao==null) {
            singerDao = new SingerDao(this);
        }
        if(localSongDao==null) {
            localSongDao = new LocalSongDao(this);
        }
        initBottomMess();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(indexBottomBarReceiver);
    }
}
