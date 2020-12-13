package com.gxuwz.beethoven.page.index.findview.songlist;

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
import android.widget.Toast;

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
import com.gxuwz.beethoven.http.CollectionesHttp;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.current.Find;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.model.entity.current.SysUser;
import com.gxuwz.beethoven.page.fragment.my.songlist.LoadDownView;
import com.gxuwz.beethoven.page.fragment.my.songlist.SongListActivity;
import com.gxuwz.beethoven.page.fragment.playlistview.PlayListView;
import com.gxuwz.beethoven.page.fragment.playview.ActivityPlayView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.BlurUtil;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.ToastUtil;
import com.gxuwz.beethoven.util.WindowPixels;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * “发现”页,推荐类歌单
 */
public class SongListFActivity extends AppCompatActivity {

    Context context;
    SharedPreferences sharedPreferences;
    LinearLayout toBackLin;

    //弹框
    LoadDownView loadDownView;

    LinearLayout diagonal,collectLin,collected,collectBox;
    ImageView songlistImg,perPic;
    TextView songlistName,username,detail,messNumber,shareNumber,playNumber,collectNumber,totalMusic;
    Songlist songlist;
    List<Song> songList;

    /**
     * 底部播放信息栏
     */
    ImageView playSonglistUrl,playAndStop,playListIv;
    TextView songNameTv,singerTv;
    IndexBottomBarReceiver indexBottomBarReceiver;
    PlayListView playListView;
    SongDao songDao;
    SingerDao singerDao;
    LocalSongDao localSongDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_song_list_f);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        init();
    }

    public void init(){
        findByIdAndNew();
        httpSonglist();
        initBottomMess();
    }

    /** 收藏操作 **/
    public void collectFun(){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==1){
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        boolean flag = jsonObject.getBoolean("flag");
                        if(flag==true){
                            collected.setVisibility(View.VISIBLE);
                            collectLin.setVisibility(View.GONE);
                        } else {
                            collected.setVisibility(View.GONE);
                            collectLin.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ToastUtil.showToast(context,"网络错误！");
                }
            }
        };
        String url = StaticHttp.BASEURL+StaticHttp.COLLECTIONES_ISCOLLECT;
        Long userId = sharedPreferences.getLong("userId",-1);
        url += "?userId="+userId+"&slId="+songlist.getSlId();
        HttpUtils.get(url,handler);
    }

    /** 收藏校验 **/
    public void collectVal(){
        collectLin = findViewById(R.id.collect_lin);
        collected = findViewById(R.id.collected);
        collectBox = findViewById(R.id.collect_box);
        SysUser sysUser = songlist.getSysUser();
        Long myUserId = sharedPreferences.getLong(StaticHttp.USER_ID,-1);
        if(myUserId == sysUser.getUserId()) {
            collectBox.setVisibility(View.INVISIBLE);
        } else {
            collectBox.setVisibility(View.VISIBLE);
            collectFun();
            collectLin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CollectionesHttp.save(context,myUserId,songlist.getSlId());
                }
            });
        }
    }

    public void httpSonglist(){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    GsonBuilder builder = new GsonBuilder();
                    builder.setDateFormat("yyyy-MM-DD");
                    Gson gson = builder.create();
                    songlist = gson.fromJson(result,Songlist.class);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        songlist.setSysUser(gson.fromJson(jsonObject.getString("sysUser"), SysUser.class));
                        collectVal();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    initSonglistMess();
                    initSlList();
                    collectFun();
                }
            }
        };
        String url = StaticHttp.BASEURL+StaticHttp.SONGLIST_FINDBYSLID;
        url += "?slId="+songlist.getSlId();
        HttpUtils.get(url,handler);
    }

    public void initSonglistMess(){
        SysUser sysUser = songlist.getSysUser();
        Glide.with(SongListFActivity.this)
                .asBitmap()
                .load(StaticHttp.STATIC_BASEURL+songlist.getCoverPicture())
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        Drawable drawable = new BitmapDrawable(BlurUtil.doBlur(MergeImage.zoomImgDB(resource, (int) WindowPixels.WIDTH,290),1,30));
                        diagonal.setBackground(drawable);
                    }

                });
        MergeImage.showGlideImgDb(context, StaticHttp.STATIC_BASEURL+songlist.getCoverPicture(),songlistImg,8);
        MergeImage.showGlideImgDb(context, StaticHttp.STATIC_BASEURL+sysUser.getAvatar(),perPic,20);
        songlistName.setText(songlist.getSlName());
        username.setText(sysUser.getUserName());
        detail.setText(songlist.getDetail());
        messNumber.setText(songlist.getPlayNumber()+"");
        shareNumber.setText(songlist.getShareNumber()+"");
        playNumber.setText(songlist.getPlayNumber()+"");
        collectNumber.setText(songlist.getColNumber()+"");
    }

    /**
     * 歌曲列表
     */
    public void initSlList(){
        RecyclerView songlistSongRv = findViewById(R.id.songlist_song_rv);
        TextView totalMusic = findViewById(R.id.total_music);
        songlistSongRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        SonglistSongAdapter songlistSongAdapter = new SonglistSongAdapter(SongListFActivity.this,songList,songlist.getSlId(),loadDownView);
        songlistSongRv.setAdapter(songlistSongAdapter);
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1){
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    GsonBuilder builder = new GsonBuilder();
                    builder.setDateFormat("yyyy-MM-DD");
                    Gson gson = builder.create();
                    Type listtype = new TypeToken<List<Song>>(){}.getType();
                    songList = gson.fromJson(result,listtype);
                    if(songlistSongAdapter!=null){
                        songlistSongRv.removeAllViews();
                        songlistSongAdapter.updateData(songList);
                    }
                    totalMusic.setText(songList.size()+"");
                }
            }
        };
        String url = StaticHttp.BASEURL+StaticHttp.SELECT_SONG_ALL;
        url += "?slId="+ URLEncoder.encode(String.valueOf(songlist.getSlId()));
        HttpUtils.get(url,handler);
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
                Intent intent = new Intent(SongListFActivity.this, ActivityPlayView.class);
                startActivity(intent);
            }
        });
        //播放列表
        playListView = new PlayListView(SongListFActivity.this,playListIv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(indexBottomBarReceiver);
    }

    public void findByIdAndNew(){
        context = SongListFActivity.this;
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        Intent intent = getIntent();
        songlist = (Songlist) intent.getSerializableExtra("songlist");
        toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        diagonal = findViewById(R.id.diagonal);
        songlistImg = findViewById(R.id.songlist_img);
        perPic = findViewById(R.id.per_pic);
        songlistName = findViewById(R.id.songlist_name);
        username = findViewById(R.id.username);
        detail = findViewById(R.id.detail);
        messNumber = findViewById(R.id.mess_number);
        shareNumber = findViewById(R.id.share_number);
        playNumber = findViewById(R.id.play_number);
        collectNumber = findViewById(R.id.collect_number);
        loadDownView = new LoadDownView(SongListFActivity.this);
        songList = new ArrayList<Song>();
        songDao = new SongDao(context);
        localSongDao = new LocalSongDao(context);
        singerDao = new SingerDao(context);
    }
}
