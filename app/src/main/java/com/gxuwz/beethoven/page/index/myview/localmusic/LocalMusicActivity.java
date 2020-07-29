package com.gxuwz.beethoven.page.index.myview.localmusic;

import android.os.Bundle;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.index.myview.localmusic.songle.SingleViewInit;

public class LocalMusicActivity extends LocalMusicBase{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);
        init();
        /**
         * 初始化右上角弹框
         */
        new RightTopPopupWindowInit(getWindowManager(),layoutInflater).init(toMany);
        /**
         * 初始化“单曲”页
         */
        new SingleViewInit(SingleView).init(this,LocalMusicActivity.this);
    }

}
