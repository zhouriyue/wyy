package com.gxuwz.beethoven.page.index.videoview.telecast;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.PagerCustomAdapter;
import com.gxuwz.beethoven.adapter.video.telecast.TagAdapter;
import com.gxuwz.beethoven.listener.video.telecast.TagChangerListenner;
import com.gxuwz.beethoven.model.entity.video.TagVideo;

import java.util.ArrayList;
import java.util.List;

/**
 * Look直播
 */
public class TelecastViewInit {

    View telecast;
    RecyclerView tagRecyclerView;
    ViewPager tagViewPager;

    List<String> tagList;
    PagerCustomAdapter pagerCustomAdapter;
    ArrayList<View> tagViewList;
    ArrayList<TagVideo> tagVideoList;

    public TelecastViewInit(View telecast) {
        this.telecast = telecast;
    }

    public void init(Context context, WindowManager windowManager){
        findByIdAndNew(context);
        viewPageInit(windowManager,context);
    }

    public void setData(Context context){
        tagList.add("热门");
        tagList.add("音乐人");
        tagList.add("校园");
        tagList.add("游戏");
        tagList.add("舞蹈");
        tagList.add("聊愈");
        tagList.add("虚拟主播");
        tagList.add("颜值");
        tagList.add("放映厅");
        tagList.add("附近");
        tagList.add("萌新");
        tagList.add("唱歌");

        for(int i = 0;i < tagList.size();i++) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.subpage_telecast_item,null);
            tagViewList.add(itemView);
        }

        TagVideo tagVideo = new TagVideo();
        tagVideo.setDiagonal("youth");
        tagVideo.setPlayNumber(2005);
        tagVideo.setTime("2002年03月21日");
        tagVideo.setTitile("正在歌唱：昨日青空");
        tagVideo.setType("好声音");
        tagVideoList.add(tagVideo);

        tagVideo = new TagVideo();
        tagVideo.setDiagonal("youth");
        tagVideo.setPlayNumber(2005);
        tagVideo.setTime("2002年03月21日");
        tagVideo.setTitile("正在歌唱：昨日青空");
        tagVideo.setType("好声音");
        tagVideoList.add(tagVideo);

        tagVideo = new TagVideo();
        tagVideo.setDiagonal("youth");
        tagVideo.setPlayNumber(2005);
        tagVideo.setTime("2002年03月21日");
        tagVideo.setTitile("正在歌唱：昨日青空");
        tagVideo.setType("好声音");
        tagVideoList.add(tagVideo);
    }

    public void viewPageInit(WindowManager windowManager,Context context){
        //绑定适配器
        tagViewPager.setAdapter(pagerCustomAdapter);
        //设置viewPager的初始界面为第一个界面
        tagViewPager.setCurrentItem(0);
        //添加切换界面的监听器
        tagViewPager.addOnPageChangeListener(new TagChangerListenner(tagRecyclerView,context,tagViewList,tagVideoList,windowManager));
        //为了获取屏幕宽度，新建一个DisplayMetrics对象
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
    }

    public void findByIdAndNew(Context context){
        tagRecyclerView = telecast.findViewById(R.id.telecast_rv);
        tagViewPager = telecast.findViewById(R.id.telecast_vp);
        tagList = new ArrayList<String>();
        tagViewList = new ArrayList<View>();
        tagVideoList = new ArrayList<TagVideo>();
        setData(context);
        pagerCustomAdapter = new PagerCustomAdapter(tagViewList);
        LinearLayoutManager ms= new LinearLayoutManager(context);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        tagRecyclerView.setLayoutManager(ms);
        TagAdapter tagAdapter = new TagAdapter(context,tagList);
        tagRecyclerView.setAdapter(tagAdapter);
    }
}
