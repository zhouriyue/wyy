package com.gxuwz.beethoven.page.fragment.my.localmusic;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.FragmentCustomAdapter;
import com.gxuwz.beethoven.adapter.my.localmusic.LocalMusicTopMenuAdapter;
import com.gxuwz.beethoven.listener.my.localmusic.TopMenuOnClickListener;
import com.gxuwz.beethoven.listener.my.localmusic.localMusicTopMenuChangeListener;
import com.gxuwz.beethoven.page.fragment.my.localmusic.album.FragmentAlbum;
import com.gxuwz.beethoven.page.fragment.my.localmusic.file.FragmentFile;
import com.gxuwz.beethoven.page.fragment.my.localmusic.singer.FragmentSinger;
import com.gxuwz.beethoven.page.fragment.my.localmusic.songle.FragmentSingle;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicBase extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    /**
     * 本地音乐头部菜单
     */
    ViewPager localMenu;

    TextView localMusicSingle;
    TextView localMusicSinger;
    TextView localMusicAlbum;
    TextView localMusicFile;
    /**
     * 返回按钮
     */
    ImageView toBack;

    List<Fragment> fragments;
    LayoutInflater layoutInflater;

    /**
     * 点击更多弹出按钮
     */
    ImageView toMany;

    public void init(){
        /**
         * 获取sharedPreferences对象
         */
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        layoutInflater = getLayoutInflater();
        findByIdAll();
        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentSingle());
        fragments.add(new FragmentSinger());
        fragments.add(new FragmentAlbum());
        fragments.add(new FragmentFile());
        localMenu.setAdapter(new FragmentCustomAdapter(getSupportFragmentManager(),fragments));
        //设置localMenu的初始界面为第一个界面
        localMenu.setCurrentItem(0);
        //添加切换界面的监听器
        localMenu.addOnPageChangeListener(new localMusicTopMenuChangeListener());
        /**
         * 监听头部菜单点击事件
         */
        TopMenuOnClickListener topMenuOnClickListener = new TopMenuOnClickListener(localMenu);
        localMusicSingle = findViewById(R.id.local_music_single);
        localMusicSinger = findViewById(R.id.local_music_singer);
        localMusicAlbum = findViewById(R.id.local_music_album);
        localMusicFile = findViewById(R.id.local_music_file);
        /**
         * 设置监听事件
         */
        localMusicSingle.setOnClickListener(topMenuOnClickListener);
        localMusicSinger.setOnClickListener(topMenuOnClickListener);
        localMusicAlbum.setOnClickListener(topMenuOnClickListener);
        localMusicFile.setOnClickListener(topMenuOnClickListener);
        /**
         * 返回上一页
         */
        toBack = findViewById(R.id.to_back);
        toBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void findByIdAll(){
        localMenu = findViewById(R.id.local_menu);
        toMany = findViewById(R.id.to_many);
    }
}
