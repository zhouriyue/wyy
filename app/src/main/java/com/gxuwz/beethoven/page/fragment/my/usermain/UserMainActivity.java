package com.gxuwz.beethoven.page.fragment.my.usermain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
import com.google.gson.reflect.TypeToken;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.my.SongListAdapter;
import com.gxuwz.beethoven.dao.SongListDao;
import com.gxuwz.beethoven.dao.SysUserDao;
import com.gxuwz.beethoven.dao.UserDetailDao;
import com.gxuwz.beethoven.model.entity.current.Songlist;
import com.gxuwz.beethoven.model.entity.current.SysUser;
import com.gxuwz.beethoven.model.vo.UserDetailVo;
import com.gxuwz.beethoven.util.DateUtil;
import com.gxuwz.beethoven.util.HttpUtil;
import com.gxuwz.beethoven.util.MergeImage;
import com.gxuwz.beethoven.util.staticdata.StaticHttp;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserMainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SysUserDao sysUserDao;
    SysUser sysUser;
    UserDetailDao userDetailDao;
    UserDetailVo userDetailVo;
    SongListDao songListDao;
    List<Songlist> songlists;
    SongListAdapter songListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        setContentView(R.layout.activity_user_main);
        /**
         * 隐藏标题栏
         * hide title box
         */
        getSupportActionBar().hide();
        init();
    }

    public void init(){
        initData();
        initPerMess();
        initSonglist();
        initTopListener();
    }

    /**
     * 初始化
     */
    public void initSonglist(){
        RecyclerView songlistRv = findViewById(R.id.songlist_rv);
        songlistRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        songlists = songListDao.findUserSL(sharedPreferences.getLong("userId",-1));
        if(songListAdapter==null){
            songListAdapter = new SongListAdapter(UserMainActivity.this, songlists);
            songlistRv.setAdapter(songListAdapter);
        }
    }

    /**
     * 初始化个人头像、昵称等信息
     */
    public void initPerMess(){
        Long userId = sharedPreferences.getLong("userId",-1);
        sysUser = sysUserDao.findById(userId);
        //头像
        ImageView avatarIv = findViewById(R.id.avatar);
        MergeImage.showGlideImgDb(UserMainActivity.this, StaticHttp.STATIC_BASEURL+sysUser.getAvatar(),avatarIv,48);
        //昵称
        TextView nicknameTv = findViewById(R.id.nick_name);
        nicknameTv.setText(sysUser.getNickName());

        /**
         * 显示用户详情
         */
        //个人空间背景
        userDetailVo = userDetailDao.findByUserId(userId);
        ImageView userBg = findViewById(R.id.user_bg);
        MergeImage.showGlideImgDb(UserMainActivity.this,StaticHttp.STATIC_BASEURL+userDetailVo.getPerBg(),userBg,1);
        //乐龄
        TextView musciYear = findViewById(R.id.music_year);
        int count = DateUtil.computeYear(sysUser.getCreateTime(),new Date());
        musciYear.setText(count+"");
        //注册时间
        TextView enrollTimeTv = findViewById(R.id.enroll_time);
        String enrollTime = DateUtil.formatYearMonthZh(sysUser.getCreateTime());
        enrollTimeTv.setText(enrollTime);
        TextView userYearTv = findViewById(R.id.user_year);
        userYearTv.setText(DateUtil.formateYear(userDetailVo.getBirthday()).substring(2));
        //星座
        TextView constellationTv = findViewById(R.id.constellation);
        constellationTv.setText(DateUtil.getAstro(userDetailVo.getBirthday()));
        //地址
        TextView addressTv = findViewById(R.id.address);
        addressTv.setText(userDetailVo.getAddress());
        //个人简介 detail
        TextView detailTv = findViewById(R.id.detail);
        detailTv.setText(userDetailVo.getDetail());
    }

    public void initTopListener(){
        LinearLayout toBackLin = findViewById(R.id.to_back_lin);
        toBackLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void initData(){
        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        sysUserDao = new SysUserDao(UserMainActivity.this);
        songListDao = new SongListDao(UserMainActivity.this);
        songlists = new ArrayList<Songlist>();
        userDetailVo = new UserDetailVo();
        userDetailDao = new UserDetailDao(UserMainActivity.this);
    }
}
