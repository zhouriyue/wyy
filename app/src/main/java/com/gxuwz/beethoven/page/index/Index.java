package com.gxuwz.beethoven.page.index;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.page.index.cloudview.CloudViewInit;
import com.gxuwz.beethoven.page.index.findview.FindViewInit;
import com.gxuwz.beethoven.page.index.myview.MyViewInit;

public class Index extends IndexBase{

    MyViewInit myViewInit;

    FindViewInit findViewInit;

    CloudViewInit cloudViewInit;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        init();
        initFunModel();
        /**
         * "我的"页面信息模块
         */
        myViewInit.init(Index.this);
        /**
         * “发现”页面模块
         */
        findViewInit.init(inflater);
        /**
         * “云村”页面模块
         */
        cloudViewInit.init();
    }

    public void initFunModel(){
        myViewInit = new MyViewInit(songList,sharedPreferences,Index.this,MyView,sysUserHandler);
        findViewInit = new FindViewInit(FindView,windowManager,this);
        cloudViewInit = new CloudViewInit(CloudView,this,windowManager,inflater);
    }
}
