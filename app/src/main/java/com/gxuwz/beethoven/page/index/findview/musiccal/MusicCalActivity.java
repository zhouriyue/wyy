package com.gxuwz.beethoven.page.index.findview.musiccal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.PagerCustomAdapter;
import com.gxuwz.beethoven.adapter.find.musiccal.McAdapter;
import com.gxuwz.beethoven.adapter.find.musiccal.NameWhiteAdapter;
import com.gxuwz.beethoven.adapter.video.NameViewAdapter;
import com.gxuwz.beethoven.listener.find.slplaza.SLPlazaListener;
import com.gxuwz.beethoven.model.entity.Song;
import com.gxuwz.beethoven.model.entity.find.musiccal.MusicCal;
import com.gxuwz.beethoven.model.entity.find.musiccal.MusicCalSong;

import java.util.ArrayList;
import java.util.List;

public class MusicCalActivity extends AppCompatActivity {

    Context context;
    WindowManager windowManager;
    RecyclerView menuNameRv;
    ViewPager musiccalVP;
    LinearLayout toBackLin;
    PagerCustomAdapter pagerCustomAdapter;
    ArrayList<View> viewList;
    List<String> nameList;
    List<MusicCal> musicCalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_music_cal);
        /**
         * 隐藏标题栏
         */
        getSupportActionBar().hide();
        findByIdAndNew();
        initPager();
    }

    public void initPager(){
        //绑定适配器
        musiccalVP.setAdapter(pagerCustomAdapter);
        //设置viewPager的初始界面为第一个界面
        musiccalVP.setCurrentItem(0);
        //添加切换界面的监听器
        musiccalVP.addOnPageChangeListener(new SLPlazaListener());
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
    }

    public void findByIdAndNew(){
        context = MusicCalActivity.this;
        windowManager = getWindowManager();
        menuNameRv = findViewById(R.id.menu_name_rv);
        musiccalVP = findViewById(R.id.musiccal_vp);
        toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewList = new ArrayList<View>();
        nameList = new ArrayList<String>();
        musicCalList = new ArrayList<MusicCal>();
        for(int i = 0;i < 5;i++) {
            viewList.add(LayoutInflater.from(context).inflate(R.layout.item_mc,null));
        }
        setData();
        initView();
        pagerCustomAdapter = new PagerCustomAdapter(viewList);
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        menuNameRv.setLayoutManager(lm);
        menuNameRv.setAdapter(new NameWhiteAdapter(context,nameList));
    }

    public void initView(){
        View view = viewList.get(0);
        RecyclerView mcRv = view.findViewById(R.id.mc_rv);
        mcRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        mcRv.setAdapter(new McAdapter(context,musicCalList));
    }

    public void setData(){
        nameList.add("7月");
        nameList.add("8月");
        nameList.add("9月");
        nameList.add("10月");
        nameList.add("11月");
        nameList.add("12月");
        nameList.add("13月");

        //1
        MusicCal musicCal = new MusicCal();
        List<MusicCalSong> musicCalSongList = new ArrayList<MusicCalSong>();
        MusicCalSong musicCalSong = new MusicCalSong();
        Song song = new Song();
        song.setImage("zhoushen");
        musicCalSong.setSong(song);
        musicCalSong.setTitle("郭靖的专辑「相思弦」");
        musicCalSongList.add(musicCalSong);

        musicCalSong = new MusicCalSong();
        song = new Song();
        song.setImage("zhoushen");
        musicCalSong.setSong(song);
        musicCalSong.setTitle("郭靖的专辑「相思弦」");
        musicCalSongList.add(musicCalSong);
        musicCal.setMusicCalSongList(musicCalSongList);
        musicCalList.add(musicCal);
        //2
        musicCal = new MusicCal();
        musicCalSongList = new ArrayList<MusicCalSong>();
        musicCalSong = new MusicCalSong();
        song = new Song();
        song.setImage("zhoushen");
        musicCalSong.setSong(song);
        musicCalSong.setTitle("郭靖的专辑「相思弦」");
        musicCalSongList.add(musicCalSong);
        musicCal.setMusicCalSongList(musicCalSongList);
        musicCalList.add(musicCal);
        //3
        musicCal = new MusicCal();
        musicCalSongList = new ArrayList<MusicCalSong>();
        musicCalSong = new MusicCalSong();
        song = new Song();
        song.setImage("zhoushen");
        musicCalSong.setSong(song);
        musicCalSong.setTitle("郭靖的专辑「相思弦」");
        musicCalSongList.add(musicCalSong);

        musicCalSong = new MusicCalSong();
        song = new Song();
        song.setImage("zhoushen");
        musicCalSong.setSong(song);
        musicCalSong.setTitle("郭靖的专辑「相思弦」");
        musicCalSongList.add(musicCalSong);
        musicCal.setMusicCalSongList(musicCalSongList);
        musicCalList.add(musicCal);
        //4
        musicCal = new MusicCal();
        musicCalSongList = new ArrayList<MusicCalSong>();
        musicCalSong = new MusicCalSong();
        song = new Song();
        song.setImage("zhoushen");
        musicCalSong.setSong(song);
        musicCalSong.setTitle("郭靖的专辑「相思弦」");
        musicCalSongList.add(musicCalSong);

        musicCalSong = new MusicCalSong();
        song = new Song();
        song.setImage("zhoushen");
        musicCalSong.setSong(song);
        musicCalSong.setTitle("郭靖的专辑「相思弦」");
        musicCalSongList.add(musicCalSong);
        musicCal.setMusicCalSongList(musicCalSongList);
        musicCalList.add(musicCal);
    }
}
