package com.gxuwz.beethoven.page.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.spefun.telecast.MoreRecommendAdapter;
import com.gxuwz.beethoven.adapter.morefun.MoreFunAdapter;
import com.gxuwz.beethoven.dao.SysUserDao;
import com.gxuwz.beethoven.model.entity.Fun;
import com.gxuwz.beethoven.model.entity.MoreFun;
import com.gxuwz.beethoven.model.entity.current.SysUser;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.util.ArrayList;
import java.util.List;

public class RecyclerMoreFun {

    Activity activity;

    View moreFunView;

    RecyclerView moreFunRv;

    List<MoreFun> moreFunList;
    SharedPreferences sharedPreferences;
    SysUserDao sysUserDao;

    public RecyclerMoreFun(Activity activity, View moreFunView) {
        this.activity = activity;
        this.moreFunView = moreFunView;
        this.sysUserDao = new SysUserDao(activity);
        sharedPreferences = activity.getSharedPreferences("data", Context.MODE_PRIVATE);
        init();
    }

    public void init(){
        setData();
        initRecyclerView();
    }

    public void initRecyclerView(){
        moreFunRv = moreFunView.findViewById(R.id.more_fun_rv);
        moreFunRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        moreFunRv.setAdapter(new MoreFunAdapter(activity,moreFunList));
    }

    public void setData(){
        moreFunList = new ArrayList<MoreFun>();
        MoreFun moreFun;
        List<Fun> funList;
        Fun fun;

        moreFun = new MoreFun();
        moreFun.setType(1);
        Long userId = sharedPreferences.getLong("userId",-1);
        SysUser sysUser = sysUserDao.findById(userId);
        moreFun.setSysUser(sysUser);
        moreFunList.add(moreFun);

        moreFun = new MoreFun();
        moreFun.setTitle("音乐服务");
        moreFun.setType(2);
        funList = new ArrayList<Fun>();
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("听歌识曲");
        fun.setStaus("可识别其他app歌曲");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("云村有票");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("商城");
        fun.setStaus("蓝牙爆款39元抢");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("游戏专区");
        fun.setStaus("手办开箱高能惊喜");
        funList.add(fun);
        moreFun.setFunList(funList);
        moreFunList.add(moreFun);

        moreFun = new MoreFun();
        moreFun.setTitle("其他");
        moreFun.setType(2);
        funList = new ArrayList<Fun>();
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("个性装扮");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("定时关机");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("扫一扫");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("边听边存");
        fun.setStaus("未开启");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("在线听歌免流量");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("音乐黑名单");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("青少年模式");
        fun.setStaus("未开启");
        funList.add(fun);
        moreFun.setFunList(funList);
        moreFunList.add(moreFun);

        moreFun = new MoreFun();
        moreFun.setTitle("其他");
        moreFun.setType(2);
        funList = new ArrayList<Fun>();
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("个性装扮");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("定时关机");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("扫一扫");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("边听边存");
        fun.setStaus("未开启");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("在线听歌免流量");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("音乐黑名单");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("青少年模式");
        fun.setStaus("未开启");
        funList.add(fun);
        moreFun.setFunList(funList);
        moreFunList.add(moreFun);

        moreFun = new MoreFun();
        moreFun.setType(2);
        moreFun.setTitle("音乐服务");
        funList = new ArrayList<Fun>();
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("听歌识曲");
        fun.setStaus("可识别其他app歌曲");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("云村有票");
        fun.setStaus("");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("商城");
        fun.setStaus("蓝牙爆款39元抢");
        funList.add(fun);
        fun = new Fun();
        fun.setIcon("youth");
        fun.setName("游戏专区");
        fun.setStaus("手办开箱高能惊喜");
        funList.add(fun);
        moreFun.setFunList(funList);

        moreFunList.add(moreFun);
    }
}
