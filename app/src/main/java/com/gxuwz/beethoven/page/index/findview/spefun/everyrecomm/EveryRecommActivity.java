package com.gxuwz.beethoven.page.index.findview.spefun.everyrecomm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.SonglistSongAdapter;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.page.fragment.find.RankingMainActivity;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownView;
import com.gxuwz.beethoven.page.fragment.my.songlist.SongListActivity;
import com.gxuwz.beethoven.page.fragment.playlistview.PlayListView;
import com.gxuwz.beethoven.page.fragment.playview.ActivityPlayView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

public class EveryRecommActivity extends AppCompatActivity {

    Context context;
    SharedPreferences sharedPreferences;

    List<Song> songList;
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

    //弹框
    LoadDownView loadDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.every_recomm);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        context = this;
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        songDao = new SongDao(context);
        singerDao = new SingerDao(context);
        localSongDao = new LocalSongDao(context);
        init();
    }

    public void init(){
        initCommponent();
        initBottomMess();
        initTodayHot();
    }

    /**
     * 初始化歌曲列表
     */
    public void initRecyclerView(){
        TextView totalMusic = findViewById(R.id.total_music);
        loadDownView = new LoadDownView(EveryRecommActivity.this);
        RecyclerView songlistSongRv = findViewById(R.id.songlist_song_rv);
        if(songList!=null&&songList.size()>0) {
            for(int i = 0;i < songList.size();i++) {
                Song song = songList.get(i);
                song.setSlId(song.getSonglistes().getSlId());
                if(songDao.findById(songList.get(i).getSongId())==null) {
                    songDao.insert(songList.get(i));
                } else {
                    songDao.update(songList.get(i));
                }
                if(!songDao.isSlToSong(song.getSonglistes().getSlId(),songList.get(i).getSongId())) {
                    songDao.insertSlToSong(song.getSonglistes().getSlId(),songList.get(i).getSongId());
                }
            }
            totalMusic.setText(songList.size()+"");
            songlistSongRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
            SonglistSongAdapter songlistSongAdapter = new SonglistSongAdapter(context,songList,songList.get(0).getSlId(),loadDownView);
            songlistSongRv.setAdapter(songlistSongAdapter);
        }
    }

    /** 获取推荐歌曲**/
    public void initTodayHot(){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("yyyy-MM-dd");
                    Gson gson = gsonBuilder.create();
                    Type listtype = new TypeToken<List<Song>>(){}.getType();
                    songList = gson.fromJson(result,listtype);
                    initRecyclerView();
                }
            }
        };
        String url = StaticHttp.BASEURL+StaticHttp.SELECT_TODAY_HOT;
        HttpUtils.get(url,handler);
    }

    public void initCommponent(){
        LinearLayout toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
                Intent intent = new Intent(EveryRecommActivity.this, ActivityPlayView.class);
                startActivity(intent);
            }
        });
        //播放列表
        playListView = new PlayListView(EveryRecommActivity.this,playListIv);
    }
}
