package com.gxuwz.beethoven.page.fragment.my.songlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.SonglistSongAdapter;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.dao.SongListDao;
import com.gxuwz.beethoven.dao.SysUserDao;
import com.gxuwz.beethoven.hanlder.SongListsMusicHandler;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.model.entity.current.SysUser;
import com.gxuwz.beethoven.page.fragment.PrincipalSheetActivity;
import com.gxuwz.beethoven.page.fragment.my.AddSongListPW;
import com.gxuwz.beethoven.page.fragment.playview.ActivityPlayView;
import com.gxuwz.beethoven.page.index.playlistview.PlayListView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

public class SongListActivity extends AppCompatActivity {

    /**
     * 正在播放歌曲信息
     */
    SharedPreferences sharedPreferences;
    /**
     * 显示用户所有歌单
     */
    RecyclerView songlistSongRv;
    /**
     * songListName: 歌单名
     * username：用户名
     * totalMusic：歌单歌曲数目
     */
    TextView songlistName,username,totalMusic;
    /**
     * perPic: 个人头像
     * songListUrl：歌单封面图片
     * toBack：返回按钮
     */
    ImageView perPic,songlistUrl,toBack,songlistBg;
    /**
     * 广播识别标志
     */
    public static final String CTL_ACTION = "CTL_ACTION";
    public static final String UPDATE_ACTION = "UPDATE_ACTION";

    //弹框
    LoadDownView loadDownView;

    /**
     * 底部播放信息栏
     */
    ImageView playSonglistUrl,playAndStop,playListIv;
    TextView songNameTv,singerTv;
    IndexBottomBarReceiver indexBottomBarReceiver;
    PlayListView playListView;

    Songlist songlist;
    List<Song> songList;
    SonglistSongAdapter songlistSongAdapter;
    SysUser sysUser;
    SongListDao songListDao;
    SysUserDao sysUserDao;
    SingerDao singerDao;
    SongDao songDao;
    LocalSongDao localSongDao;
    Long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_song_list);
        /**
         * 隐藏标题栏
         * hide title box
         */
        getSupportActionBar().hide();

        init();
    }

    public void init(){
        findByIdAll();
        initData();
        //获取上一页的参数
        getParamter();
        initSlList();
        initCreateBy();
        initSlMess();
        initBottomMess();
        initOnClick();
    }

    /**
     * 初始化歌单创始人
     */
    public void initCreateBy(){
        sysUser = sysUserDao.findById(userId);
        if(sysUser!=null) {
            MergeImage.showGlideImgDb(SongListActivity.this,StaticHttp.STATIC_BASEURL+sysUser.getAvatar(),perPic,13);
            username.setText(sysUser.getUserName());
        }
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        if(sysUser==null) {
                            sysUser = new SysUser();
                        }
                        sysUser.setUserId(jsonObject.getLong("userId"));
                        sysUser.setUserName(jsonObject.getString("userName"));
                        sysUser.setNickName(jsonObject.getString("nickName"));
                        sysUser.setEmail(jsonObject.getString("email"));
                        sysUser.setPhonenumber(jsonObject.getString("phonenumber"));
                        sysUser.setSex(jsonObject.getString("sex"));
                        sysUser.setAvatar(jsonObject.getString("avatar"));
                        String avater = StaticHttp.STATIC_BASEURL+sysUser.getAvatar();
                        MergeImage.showGlideImgDb(SongListActivity.this,avater,perPic,13);
                        username.setText(sysUser.getUserName());
                        if(sysUserDao.findById(sysUser.getUserId())==null) {
                            sysUserDao.insert(sysUser);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        String url = StaticHttp.SYSTEM_BASEURL+StaticHttp.GET_SYSTEM;
        url += "?userId="+userId;
        HttpUtil.get(url,handler);

    }

    /**
     * 初始化歌单信息
     */
    public void initSlMess(){
        songlistName.setText(songlist.getSlName());
        if(songlist.getCoverPicture()==null) {
            if(songlist.getSongNumber()>0) {
                Song song = songDao.findIndexSong(songlist.getSlId());
                if(song==null) {
                    Handler handler = new Handler(){
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            if(msg.what==1) {
                                Bundle bundle = msg.getData();
                                String result = bundle.getString("result");
                                try {
                                    JSONObject jsonObject = new JSONObject(result);
                                    String coverPicture = jsonObject.getString("coverPicture");
                                    if(coverPicture!=null) {
                                        MergeImage.showGlideImgDb(SongListActivity.this,StaticHttp.STATIC_BASEURL+coverPicture,songlistUrl,10);
                                        MergeImage.doBlur(SongListActivity.this,StaticHttp.STATIC_BASEURL+coverPicture,songlistBg,3,100);
                                    } else {
                                        MergeImage.showGlideImgDb(SongListActivity.this,StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,songlistUrl,10);
                                        MergeImage.doBlur(SongListActivity.this,StaticHttp.STATIC_BASEURL+coverPicture,songlistBg,3,100);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    String url = StaticHttp.BASEURL+StaticHttp.SELECT_INDEX_SONG;
                    url += "?slId="+songlist.getSlId();
                    HttpUtil.get(url,handler);
                } else {
                    String coverPicture = StaticHttp.STATIC_BASEURL+song.getCoverPicture();
                    MergeImage.showGlideImgDb(this,coverPicture,songlistUrl,10);
                    MergeImage.doBlur(this,coverPicture,songlistBg,3,100);
                }
            } else {
                MergeImage.showGlideImgDb(this,StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,songlistUrl,10);
                MergeImage.doBlur(this,StaticHttp.DEFALUT_SONGLIST_COVERPICTURE,songlistBg,3,100);
            }
        } else {
            String coverPicture = StaticHttp.STATIC_BASEURL+songlist.getCoverPicture();
            MergeImage.showGlideImgDb(this,coverPicture,songlistUrl,10);
            MergeImage.doBlur(this,coverPicture,songlistBg,3,100);
        }
    }

    /**
     * 歌单列表
     */
    public void initSlList(){
        songList = songDao.findSonglistSong(songlist.getSlId());
        songlistSongRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        if(songlistSongAdapter==null) {
            songlistSongAdapter = new SonglistSongAdapter(SongListActivity.this,songList,songlist.getSlId(),loadDownView);
            songlistSongRv.setAdapter(songlistSongAdapter);
        }
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1){
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    Gson gson = new Gson();
                    Type listtype = new TypeToken<List<Song>>(){}.getType();
                    songList = gson.fromJson(result,listtype);
                    if(songlistSongAdapter!=null){
                        songlistSongRv.removeAllViews();
                        songlistSongAdapter.updateData(songList);
                    }
                    for(int i = 0;i < songList.size();i++) {
                        if(songDao.findById(songList.get(i).getSongId())==null) {
                            songDao.insert(songList.get(i));
                        }
                        if(!songDao.isSlToSong(songlist.getSlId(),songList.get(i).getSongId())) {
                            songDao.insertSlToSong(songlist.getSlId(),songList.get(i).getSongId());
                        }
                    }
                    totalMusic.setText(songList.size()+"");
                }
            }
        };
        String url = StaticHttp.BASEURL+StaticHttp.SELECT_SONG_ALL;
        url += "?slId="+ URLEncoder.encode(String.valueOf(songlist.getSlId()));
        HttpUtil.get(url,handler);
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
                Intent intent = new Intent(SongListActivity.this, ActivityPlayView.class);
                startActivity(intent);
            }
        });
        //播放列表
        playListView = new PlayListView(SongListActivity.this,playListIv);
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(indexBottomBarReceiver);
    }

    public void initOnClick(){
        toBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getParamter(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        songlist = (Songlist) bundle.getSerializable("songlist");
    }

    public void initData(){
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        songListDao = new SongListDao(this);
        sysUserDao = new SysUserDao(this);
        singerDao = new SingerDao(this);
        songDao = new SongDao(this);
        localSongDao = new LocalSongDao(this);
        userId = sharedPreferences.getLong("userId",-1);
        loadDownView = new LoadDownView(SongListActivity.this);
    }

    public void findByIdAll(){
        songlistName = findViewById(R.id.song_list_name);
        username = findViewById(R.id.username);
        totalMusic = findViewById(R.id.total_music);
        perPic = findViewById(R.id.per_pic);
        songlistUrl = findViewById(R.id.song_list_url);
        toBack = findViewById(R.id.to_back);
        songlistBg = findViewById(R.id.song_list_bg);
        songlistSongRv = findViewById(R.id.songlist_song_rv);
    }

}
