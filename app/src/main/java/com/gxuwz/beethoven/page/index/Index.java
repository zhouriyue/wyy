package com.gxuwz.beethoven.page.index;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.index.findview.FindViewInit;
import com.gxuwz.beethoven.page.index.myview.MyViewInit;

public class Index extends IndexBase{

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        init();
        /**
         * "我的"页面信息模块
         */
        new MyViewInit(songList,sharedPreferences,Index.this,MyView,sysUserHandler).init(Index.this);
        /**
         * “发现”页面模块
         */
        new FindViewInit(FindView,windowManager,this).init(inflater);
    }
}
