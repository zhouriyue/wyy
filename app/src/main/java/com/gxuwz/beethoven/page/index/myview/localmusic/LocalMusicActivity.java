package com.gxuwz.beethoven.page.index.myview.localmusic;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.index.myview.localmusic.album.AlbumViewInit;
import com.gxuwz.beethoven.page.index.myview.localmusic.singer.SingerViewInit;
import com.gxuwz.beethoven.page.index.myview.localmusic.songle.SingleViewInit;

/**
 * 本地音乐
 */
public class LocalMusicActivity extends LocalMusicBase{

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
        /**
         * 初始化右上角弹框
         */
        new RightTopPopupWindowInit(getWindowManager(),layoutInflater).init(toMany);
        /**
         * 初始化“单曲”页
         */
        new SingleViewInit(SingleView,sharedPreferences).init(this,LocalMusicActivity.this);
        /**
         * 初始化“歌手”页
         */
        new SingerViewInit(SingerView).init(LocalMusicActivity.this);
        /**
         * 初始化“专辑”页
         */
        new AlbumViewInit(AlbumView).init(LocalMusicActivity.this);
    }

}
