package com.gxuwz.beethoven.page.index.myview.localmusic;

import android.os.Bundle;
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
        setContentView(R.layout.activity_my_local_music);
        init();
        /**
         * 初始化右上角弹框
         */
        new RightTopPopupWindowInit(getWindowManager(),layoutInflater).init(toMany);
        /**
         * 初始化“单曲”页
         */
        new SingleViewInit(SingleView).init(this,LocalMusicActivity.this);
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
