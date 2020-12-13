package com.gxuwz.beethoven.page.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.FragmentCustomAdapter;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.page.fragment.find.FragmentFind;
import com.gxuwz.beethoven.page.fragment.my.FragmentMy;
import com.gxuwz.beethoven.page.fragment.video.FragmentVideo;
import com.gxuwz.beethoven.page.fragment.yunvillage.FragmentYunVillage;
import com.gxuwz.beethoven.page.fragment.playlistview.PlayListView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.ArrayList;
import java.util.List;

public class PrincipalSheetActivity extends FragmentActivity implements View.OnClickListener {

    PlayListView playListView;
    ImageView moreImageView;
    MoreFunView moreFunView;

    BasicSet basicSet;

    ViewPager viewPager;
    List<Fragment> fragmentList;
    ImageView playListIV;
    TextView[] textViews;
    SharedPreferences sharedPreferences;
    IndexBottomBarReceiver indexBottomBarReceiver;
    SongDao songDao;
    SingerDao singerDao;
    LocalSongDao localSongDao;
    int last = 0;

    /**
     * 底部播放信息栏
     */
    ImageView playSonglistUrl,playAndStop,playListIv;
    TextView songNameTv,singerTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_sheet);
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        songDao = new SongDao(this);
        singerDao = new SingerDao(this);
        localSongDao = new LocalSongDao(this);
        init();
        initViewPager();
    }

    public void init() {
        findByIdAll();
        initBaseData();
        initFun();
        initBottomMess();
        initMusicService();
    }

    public void initMusicService(){
        /**
         /**
         * 初始化音乐service
         */
        Intent music = new Intent(this, MusicService.class);
        startService(music);
    }

    //初始化功能模块
    public void initFun(){
        //播放列表
        playListView = new PlayListView(PrincipalSheetActivity.this,playListIV);
        //更多功能
        moreFunView = new MoreFunView(PrincipalSheetActivity.this);
    }

    private void initViewPager(){
        viewPager.setOffscreenPageLimit(0);
        fragmentList = new ArrayList<Fragment>();
        Fragment fragmentMy = new FragmentMy();
        Fragment fragmentFind = new FragmentFind();
        Fragment fragmentYunVillage = new FragmentYunVillage();
        Fragment fragmentVideo = new FragmentVideo();
        fragmentList.add(fragmentMy);
        fragmentList.add(fragmentFind);
        fragmentList.add(fragmentYunVillage);
        fragmentList.add(fragmentVideo);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new FragmentCustomAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setCurrentItem(0);
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
                    MergeImage.showGlideImgDb(this, StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,playSonglistUrl,19);
                }
            }
        } else {
            LocalSong localSong = localSongDao.findBySongId(songId);
            if(localSong!=null) {
                MergeImage.showGlideImgDb(this, StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,playSonglistUrl,19);
                songNameTv.setText(localSong.getSongName());
                singerTv.setText(localSong.getSingerName());
            }
        }
        indexBottomBarReceiver = new IndexBottomBarReceiver(playSonglistUrl, songNameTv, singerTv);
        indexBottomBarReceiver.setPlayAndStop(playAndStop);
        IntentFilter indexBottomBar = new IntentFilter(IndexBottomBarReceiver.ACTION);
        registerReceiver(indexBottomBarReceiver, indexBottomBar);
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
                if (Player.isPlayer) {
                    MusicService.musicCtrl(PrincipalSheetActivity.this,MusicService.STOP);
                    IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_STOP,PrincipalSheetActivity.this);
                } else {
                    MusicService.musicCtrl(PrincipalSheetActivity.this,MusicService.PLAY);
                    IndexBottomBarReceiver.sendBroadcast(IndexBottomBarReceiver.FLAT_PLAY,PrincipalSheetActivity.this);
                }
            }
        });
        if (Player.isPlayer) {
            MergeImage.showGlideImgDb(this,R.drawable.stop_bar,playAndStop,1);
        } else {
            MergeImage.showGlideImgDb(this,R.drawable.icon_play1,playAndStop,1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(indexBottomBarReceiver);
    }

    public void initBaseData(){
        textViews = new TextView[4];
        textViews[0] = findViewById(R.id.my_menu);
        textViews[1] = findViewById(R.id.find_menu);
        textViews[2] = findViewById(R.id.cloud_menu);
        textViews[3] = findViewById(R.id.video_menu);
        /**
         * 底部播放或停止按钮变化
         */
        textViews[0].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textViews[0].setTextColor(Color.parseColor("#ffffff"));
        moreImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreFunView.showAtLocation(v);
            }
        });
        basicSet = new BasicSet(PrincipalSheetActivity.this);
    }

    public void findByIdAll() {
        playListIV = findViewById(R.id.play_list);
        viewPager = findViewById(R.id.view_pager);
        moreImageView = findViewById(R.id.more_fun);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_menu:{
                viewPager.setCurrentItem(0);
                textViews[last].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                last = 0;
                textViews[0].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            };break;
            case R.id.find_menu:{
                viewPager.setCurrentItem(1);
                textViews[last].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                last = 1;
                textViews[1].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            };break;
            case R.id.cloud_menu:{
                viewPager.setCurrentItem(2);
                textViews[last].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                last = 2;
                textViews[2].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            };break;
            case R.id.video_menu:{
                viewPager.setCurrentItem(3);
                textViews[last].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                last = 3;
                textViews[3].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            };break;
        }
    }
}
