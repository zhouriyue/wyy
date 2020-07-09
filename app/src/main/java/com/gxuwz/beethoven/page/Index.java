package com.gxuwz.beethoven.page;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.hanlder.SysUserHandler;
import com.gxuwz.beethoven.util.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Index extends Activity implements View.OnClickListener {


    private ViewPager viewPager;
    Map<String,Integer> map = new HashMap<String, Integer>();
    private ArrayList<View> pageview;
    private TextView videoLayout;
    private TextView musicLayout;
    private TextView my;
    // 滚动条图片
    private ImageView scrollbar;
    // 滚动条初始偏移量
    private int offset = 0;
    // 当前页编号
    private int currIndex = 0;
    // 滚动条宽度
    private int bmpW;
    //一倍滚动量
    private int one;
    //前一个页面
    private int pre;
    private TextView[] textViews;
    SysUserHandler sysUserHandler = new SysUserHandler();
    Message msgSysUser;
    ImageView perPicView;
    TextView userNameView;
    LayoutInflater inflater;
    RecyclerView songList;
    /**
     * 本地图片
     */
    ImageView localMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //查找布局文件用LayoutInflater.inflate
        inflater =getLayoutInflater();
        View View_My = inflater.inflate(R.layout.activity_my, null);
        View view2 = inflater.inflate(R.layout.media_player, null);
        View view3 = inflater.inflate(R.layout.video_player, null);



        videoLayout = (TextView)findViewById(R.id.videoLayout);
        musicLayout = (TextView)findViewById(R.id.musicLayout);
        scrollbar = (ImageView)findViewById(R.id.scrollbar);
        my = (TextView)findViewById(R.id.my);
        videoLayout.setOnClickListener(this);
        musicLayout.setOnClickListener(this);
        my.setOnClickListener(this);

        pageview =new ArrayList<View>();
        //添加想要切换的界面
        pageview.add(View_My);
        pageview.add(view2);
        pageview.add(view3);

        textViews = new TextView[pageview.size()];
        textViews[0] = findViewById(R.id.my);
        textViews[1] = findViewById(R.id.musicLayout);
        textViews[2] = findViewById(R.id.videoLayout);

        /**
         * 页面信息
         */

        //数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter(){

            @Override
            //获取当前窗体界面数
            public int getCount() {
                // TODO Auto-generated method stub
                return pageview.size();
            }

            @Override
            //判断是否由对象生成界面
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0==arg1;
            }
            //使从ViewGroup中移出当前View
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView(pageview.get(arg1));
            }

            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            public Object instantiateItem(View arg0, int arg1){
                ((ViewPager)arg0).addView(pageview.get(arg1));
                return pageview.get(arg1);
            }
        };
        textViews[0].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        textViews[0].setTextColor(Color.parseColor("#000000"));
        //绑定适配器
        viewPager.setAdapter(mPagerAdapter);
        //设置viewPager的初始界面为第一个界面
        viewPager.setCurrentItem(0);
        //添加切换界面的监听器
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        // 获取滚动条的宽度
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.main).getWidth();
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        initMy(View_My);
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            textViews[pre].setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            textViews[pre].setTextColor(Color.parseColor("#616161"));
            switch (arg0) {
                case 0:
                    /**
                     * TranslateAnimation的四个属性分别为
                     * float fromXDelta 动画开始的点离当前View X坐标上的差值
                     * float toXDelta 动画结束的点离当前View X坐标上的差值
                     * float fromYDelta 动画开始的点离当前View Y坐标上的差值
                     * float toYDelta 动画开始的点离当前View Y坐标上的差值
                     **/
                    animation = new TranslateAnimation(one, 0, 0, 0);

                    break;
                case 1:
                    animation = new TranslateAnimation(offset, one, 0, 0);

                    break;
                case 2:
                    animation = new TranslateAnimation(one, offset, 0, 0);
                    break;
            }
            //arg0为切换到的页的编码
            currIndex = arg0;
            textViews[currIndex].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            textViews[currIndex].setTextColor(Color.parseColor("#000000"));
            pre = arg0;
            // 将此属性设置为true可以使得图片停在动画结束时的位置
            animation.setFillAfter(true);
            //动画持续时间，单位为毫秒
            animation.setDuration(200);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.my:
                //点击"我的“时切换到第一页
                viewPager.setCurrentItem(0);
                break;
            case R.id.musicLayout:
                //点击“视频”时切换的第二页
                viewPager.setCurrentItem(1);
                break;
            case R.id.videoLayout:
                //点击“音乐”时切换的第二页
                viewPager.setCurrentItem(2);
                break;
        }
    }

    public void initMy(View my){
        String url = "http://10.0.2.2:8082/sysUserRest/zhouriyue";
        //首先通过inflate得到各个子view的对象
        userNameView = my.findViewById(R.id.username);
        perPicView = my.findViewById(R.id.per_pic);
        songList = my.findViewById(R.id.rv3);
        /**
         * 本地图片
         */
        localMusic = my.findViewById(R.id.local_music);
        localMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Index.this, LocalMusicActivity.class);
                startActivity(intent);
            }
        });
        sysUserHandler.setUserNameView(userNameView);
        sysUserHandler.setPerPicView(perPicView);
        sysUserHandler.setSongList(songList);
        sysUserHandler.setContext(Index.this);
        HttpUtil.get(url,sysUserHandler);
    }

    private Bitmap getRes(String imageName) {
        ApplicationInfo appInfo = this.getApplicationInfo();
        int resID = getResources().getIdentifier(imageName, "drawable", appInfo.packageName);
        return BitmapFactory.decodeResource(getResources(), resID);
    }

}
