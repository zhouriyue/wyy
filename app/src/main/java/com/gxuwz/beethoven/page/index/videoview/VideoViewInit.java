package com.gxuwz.beethoven.page.index.videoview;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.PagerCustomAdapter;
import com.gxuwz.beethoven.adapter.video.NameViewAdapter;
import com.gxuwz.beethoven.listener.video.VideoChangerListener;
import com.gxuwz.beethoven.model.entity.video.VideoBox;

import java.util.ArrayList;
import java.util.List;

public class VideoViewInit {
    View videoView;
    ViewPager videoPager;
    RecyclerView viewNameRv;

    LayoutInflater layoutInflater;
    WindowManager windowManager;
    /**
     * 子页面列表
     */
    ArrayList<View> subPages;
    List<String> viewNameList;
    List<VideoBox> videoBoxList;

    PagerCustomAdapter pagerCustomAdapter;

    VideoBoxItem videoBoxItem;

    public VideoViewInit(View videoView,LayoutInflater layoutInflater,WindowManager windowManager) {
        this.videoView = videoView;
        this.layoutInflater = layoutInflater;
        this.windowManager = windowManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void init(Context context){
        findByIdAndNew(context);
        viewPagerInit(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void findByIdAndNew(Context context){
        videoPager = videoView.findViewById(R.id.video_pager);
        viewNameRv = videoView.findViewById(R.id.name_view_rv);
        subPages = new ArrayList<View>();
        videoBoxList = new ArrayList<VideoBox>();
        viewNameList = new ArrayList<>();
        setData();
        showSubPager();
        pagerCustomAdapter = new PagerCustomAdapter(subPages);
        LinearLayoutManager ms= new LinearLayoutManager(context);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        viewNameRv.setLayoutManager(ms);
        NameViewAdapter nameViewAdapter = new NameViewAdapter(viewNameList,context);
        viewNameRv.setAdapter(nameViewAdapter);
    }

    public void showSubPager(){
        for(int i = 0;i < viewNameList.size();i++) {
            View pagerItem = null;
            switch (i) {
                case 1:{
                    pagerItem = layoutInflater.inflate(R.layout.subpage_telecast,null);
                };break;
                case 6:{
                    pagerItem = layoutInflater.inflate(R.layout.subpage_official,null);
                };break;
                case 7:{
                    pagerItem = layoutInflater.inflate(R.layout.subpage_plazaadvert,null);
                };break;
                case 8:{
                    pagerItem = layoutInflater.inflate(R.layout.subpage_mv,null);
                };break;
                default:{
                    pagerItem = layoutInflater.inflate(R.layout.sub_pages_item,null);
                };break;
            }
            subPages.add(pagerItem);
        }
    }

    public void viewPagerInit(Context context){
        //绑定适配器
        videoPager.setAdapter(pagerCustomAdapter);
        //设置viewPager的初始界面为第一个界面
        videoPager.setCurrentItem(0);
        //添加切换界面的监听器
        videoPager.addOnPageChangeListener(new VideoChangerListener(viewNameRv,subPages,context,windowManager));
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

    }

    public void setData() {
        VideoBox videoBox = new VideoBox();
        videoBox.setDiagonal("youth");
        videoBox.setGetLike(23);
        videoBox.setPlayNumber(21345);
        videoBox.setPlayTime("12:00");
        videoBox.setMessNumber(25);
        videoBox.setTag("#人气榜飙升榜#");
        videoBox.setTitle("周深《爱若琉璃》混剪 电视剧 《琉璃》 主题曲");
        videoBoxList.add(videoBox);

        videoBox = new VideoBox();
        videoBox.setDiagonal("youth");
        videoBox.setGetLike(23);
        videoBox.setPlayNumber(21345);
        videoBox.setPlayTime("12:00");
        videoBox.setMessNumber(25);
        videoBox.setTag("#人气榜飙升榜#");
        videoBox.setTitle("周深《爱若琉璃》混剪 电视剧 《琉璃》 主题曲");
        videoBoxList.add(videoBox);

        viewNameList.add("推荐");
        viewNameList.add("LOOK直播");
        viewNameList.add("现场");
        viewNameList.add("翻唱");
        viewNameList.add("舞蹈");
        viewNameList.add("听BGM");
        viewNameList.add("官方");
        viewNameList.add("广场");
        viewNameList.add("MV");
        viewNameList.add("生活");
        viewNameList.add("游戏");
        viewNameList.add("ACG音乐");
        viewNameList.add("最佳饭制");
    }
}
