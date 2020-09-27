package com.gxuwz.beethoven.page.index.cloudview;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.cloud.PlazaAdapter;
import com.gxuwz.beethoven.adapter.my.RecentBroadcastsAdapter;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.mlog.ImageWordMlog;
import com.gxuwz.beethoven.model.entity.mlog.Mlog;

import java.util.ArrayList;
import java.util.List;

public class PlazaViewInit {
    /**
     * 广场
     */
    RecyclerView plazaView;

    Context context;
    WindowManager windowManager;
    View plaza;
    List<Mlog> mlogList;

    public PlazaViewInit(Context context, View plaza,WindowManager windowManager) {
        this.context = context;
        this.plaza = plaza;
        this.windowManager = windowManager;
    }

    public void init() {
        findByIdAndNew();
        setData();
        recyclerInit();
    }

    public void setData(){
        ImageWordMlog imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("zhoushen1");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        SysUser sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("zhoushen");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("mlog");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("mlog");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("mlog");
        imageWordMlog.setContent("张一山演技炸裂表演 终于找到出处了");
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUser.setUserName("zhouriyue");
        imageWordMlog.setSysUser(sysUser);
        imageWordMlog.setLikeNumber(230);
        mlogList.add(imageWordMlog);
    }

    public void findByIdAndNew(){
        plazaView = plaza.findViewById(R.id.plaza_recycler_view);
        mlogList = new ArrayList<Mlog>();
    }

    public void recyclerInit(){
        plazaView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        plazaView.setNestedScrollingEnabled(false);
        plazaView.setAdapter(new PlazaAdapter(context,mlogList,windowManager));
    }
}
