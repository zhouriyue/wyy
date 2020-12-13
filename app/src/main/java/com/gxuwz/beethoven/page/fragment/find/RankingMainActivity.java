package com.gxuwz.beethoven.page.fragment.find;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.SonglistSongAdapter;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.RankingList;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownView;
import com.gxuwz.beethoven.page.fragment.my.songlist.SongListActivity;
import com.gxuwz.beethoven.page.fragment.playlistview.PlayListView;
import com.gxuwz.beethoven.page.fragment.playview.ActivityPlayView;
import com.gxuwz.beethoven.page.index.findview.songlist.SongListFActivity;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.BlurUtil;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.WindowPixels;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

public class RankingMainActivity extends AppCompatActivity {

    Context context;
    SharedPreferences sharedPreferences;
    RankingList rankingList;
    SongDao songDao;
    SingerDao singerDao;
    LocalSongDao localSongDao;

    /**
     * 底部播放信息栏
     */
    ImageView playSonglistUrl,playAndStop,playListIv;
    TextView songNameTv,singerTv;
    IndexBottomBarReceiver indexBottomBarReceiver;
    PlayListView playListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_ranking_main);
        getSupportActionBar().hide();
        context = this;
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        songDao = new SongDao(context);
        singerDao = new SingerDao(context);
        localSongDao = new LocalSongDao(context);
        init();
    }

    public void init(){
        initRankingMess();
        initSongList();
        initBottomMess();
    }

    public void initSongList(){
        RecyclerView songlistSongRv = findViewById(R.id.songlist_song_rv);
        //弹框
        LoadDownView loadDownView = new LoadDownView(context);
        songlistSongRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        TextView totalMusic = findViewById(R.id.total_music);
        if(rankingList.getSongList()!=null) {
            List<Song> songList = rankingList.getSongList();
            for(int i = 0;i < songList.size();i++) {
                Song song = songList.get(i);
                if(songDao.findById(song.getSongId())==null) {
                    songDao.insert(song);
                } else {
                    songDao.update(song);
                }
                if(!songDao.isSlToSong(song.getSonglistes().getSlId(),song.getSongId())) {
                    songDao.insertSlToSong(song.getSonglistes().getSlId(),song.getSongId());
                }
            }
            SonglistSongAdapter songlistSongAdapter = new SonglistSongAdapter(context,rankingList.getSongList(),rankingList.getRlId(),loadDownView);
            songlistSongRv.setAdapter(songlistSongAdapter);
        }
        if(rankingList.getSongList()!=null) {
            totalMusic.setText(rankingList.getSongList().size()+"");
        } else {
            totalMusic.setText("0");
        }
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
                Intent intent = new Intent(RankingMainActivity.this, ActivityPlayView.class);
                startActivity(intent);
            }
        });
        //播放列表
        playListView = new PlayListView(RankingMainActivity.this,playListIv);
    }

    /** 获取排行榜基本信息 **/
    public void initRankingMess(){
        LinearLayout linearLayout = findViewById(R.id.bg);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置白色背景
        ImageView blusImg,rlImg;
        blusImg = findViewById(R.id.blus_img);
        rlImg = findViewById(R.id.rl_img);
        Intent intent = getIntent();
        rankingList = (RankingList) intent.getSerializableExtra("rankingList");
        MergeImage.showGlideImgDb(context,StaticHttp.STATIC_BASEURL+rankingList.getSlPicture(),rlImg,2);
        MergeImage.showGlideImgDb(context,StaticHttp.STATIC_BASEURL+rankingList.getSlPicture(),blusImg,1);
        blusImg.setAlpha(0.75f);
        Glide.with(context)
                .asBitmap()
                .load(StaticHttp.STATIC_BASEURL+rankingList.getSlPicture())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Drawable drawable = new BitmapDrawable(BlurUtil.doBlur(MergeImage.zoomImgDB(resource, (int) WindowPixels.WIDTH,245),1,30));
                        linearLayout.setBackground(drawable);
                    }
                });

        //排行榜名
        TextView slName = findViewById(R.id.rl_name);
        TextView detail = findViewById(R.id.detail);
        slName.setText(rankingList.getRlName());
        detail.setText(rankingList.getDetail());
    }
}
