package com.gxuwz.beethoven.page.index.findview.spefun.rankinglist;

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
import com.gxuwz.beethoven.adapter.find.spefun.rankinglist.RLItemAdapter;
import com.gxuwz.beethoven.dao.LocalSongDao;
import com.gxuwz.beethoven.dao.SingerDao;
import com.gxuwz.beethoven.dao.SongDao;
import com.gxuwz.beethoven.model.entity.current.LocalSong;
import com.gxuwz.beethoven.model.entity.current.RankingList;
import com.gxuwz.beethoven.model.entity.current.Singer;
import com.gxuwz.beethoven.model.entity.current.Song;
import com.gxuwz.beethoven.model.entity.find.spefun.rankinglist.RankingListShow;
import com.gxuwz.beethoven.page.fragment.find.RankingMainActivity;
import com.gxuwz.beethoven.page.fragment.playlistview.PlayListView;
import com.gxuwz.beethoven.page.fragment.playview.ActivityPlayView;
import com.gxuwz.beethoven.receiver.IndexBottomBarReceiver;
import com.gxuwz.beethoven.service.MusicService;
import com.gxuwz.beethoven.util.HttpUtils;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.Player;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RankingListActivity extends AppCompatActivity {

    Context context;
    SharedPreferences sharedPreferences;

    LinearLayout toBackLin;
    RecyclerView rankingListRv;
    List<RankingListShow> rklShowList;
    boolean recomm = false,off = false,all = false;

    /**
     * 底部播放信息栏
     */
    ImageView playSonglistUrl,playAndStop,playListIv;
    TextView songNameTv,singerTv;
    IndexBottomBarReceiver indexBottomBarReceiver;
    PlayListView playListView;
    SongDao songDao;
    LocalSongDao localSongDao;
    SingerDao singerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.rankinglist);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        context = this;
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        songDao = new SongDao(context);
        localSongDao = new LocalSongDao(context);
        singerDao = new SingerDao(context);
        init();
    }

    public void init(){
        findByIdAndNew();
        initData();
        initBottomMess();
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
                Intent intent = new Intent(RankingListActivity.this, ActivityPlayView.class);
                startActivity(intent);
            }
        });
        //播放列表
        playListView = new PlayListView(RankingListActivity.this,playListIv);
    }

    public void initData(){
        //获取推荐的排行榜
        getRecomm();
        //获取官方排行榜
        getOff();
    }

    public void findByIdAndNew(){
        rankingListRv = findViewById(R.id.rankinglist_rv);
        rklShowList = new ArrayList<RankingListShow>();
        setData();
        toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setData(){
        /**
         * 竖着显示
         */
        /*rankingListShow = new RankingListShow();
        rankingListShow.setShowType(2);
        rankingListShow.setTitle("官方榜");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        List<Song> songList = new ArrayList<Song>();
        Song song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        rankingList.setSongList(songList);
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        songList = new ArrayList<Song>();
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        rankingList.setSongList(songList);
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        songList = new ArrayList<Song>();
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        song = new Song();
        song.setSongName("他只是经过");
        song.setSinger("h3R3/Felix Bennett");
        songList.add(song);
        rankingList.setSongList(songList);
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);

        //第三个数据
        rankingListShow = new RankingListShow();
        rankingListShow.setShowType(1);
        rankingListShow.setTitle("榜单推荐");
        rankingListList = new ArrayList<RankingList>();
        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);

        rankingList = new RankingList();
        rankingList.setName("硬地原创歌曲");
        rankingList.setImg("youth");
        rankingList.setUpdateType("每天更新");
        rankingListList.add(rankingList);
        rankingListShow.setRankingListList(rankingListList);
        rklShowList.add(rankingListShow);*/
    }

    /** 获取全球排行榜 **/
    public void getSevenSeas(){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("yyyy-MM-dd");
                    Gson gson = gsonBuilder.create();
                    Type listType = new TypeToken<List<RankingList>>(){}.getType();
                    RankingListShow rankingListShow = new RankingListShow();
                    rankingListShow.setShowType(1);
                    rankingListShow.setTitle("全球榜");
                    List<RankingList> rankingListList = gson.fromJson(result,listType);
                    rankingListShow.setRankingListList(rankingListList);
                    rklShowList.add(rankingListShow);
                    all = true;
                    if (recomm&&off&&all){
                        initRecyclerView();
                    }
                }
            }
        };
        String url = StaticHttp.BASEURL + StaticHttp.GETNOTOFF;
        HttpUtils.get(url,handler);
    }

    /** 获取官方排行榜 **/
    public void getOff(){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("yyyy-MM-dd");
                    Gson gson = gsonBuilder.create();
                    Type listType = new TypeToken<List<RankingList>>(){}.getType();
                    RankingListShow rankingListShow = new RankingListShow();
                    rankingListShow.setShowType(2);
                    rankingListShow.setTitle("官方榜");
                    List<RankingList> rankingListList = gson.fromJson(result,listType);
                    rankingListShow.setRankingListList(rankingListList);
                    rklShowList.add(rankingListShow);
                    off = true;
                    if (recomm&&off&&all){
                        initRecyclerView();
                    }
                    //获取全球排行榜
                    getSevenSeas();
                }
            }
        };
        String url = StaticHttp.BASEURL + StaticHttp.GETOFFRANKINGLIST;
        HttpUtils.get(url,handler);
    }

    /** 获取推荐排行榜 **/
    public void getRecomm(){
        Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what==1) {
                    Bundle bundle = msg.getData();
                    String result = bundle.getString("result");
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("yyyy-MM-dd");
                    Gson gson = gsonBuilder.create();
                    Type listType = new TypeToken<List<RankingList>>(){}.getType();
                    RankingListShow rankingListShow = new RankingListShow();
                    rankingListShow.setShowType(1);
                    rankingListShow.setTitle("榜单推荐");
                    List<RankingList> rankingListList = gson.fromJson(result,listType);
                    rankingListShow.setRankingListList(rankingListList);
                    rklShowList.add(rankingListShow);
                    recomm = true;
                    if (recomm&&off&&all){
                        initRecyclerView();
                    }
                }
            }
        };
        String url = StaticHttp.BASEURL + StaticHttp.RANKINGLIST_GETHOTRANKINGLIST;
        url += "?pageNum=0&size=3";
        HttpUtils.get(url,handler);
    }

    public void initRecyclerView(){
        rankingListRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        rankingListRv.setAdapter(new RLItemAdapter(RankingListActivity.this,rklShowList));
    }
}
