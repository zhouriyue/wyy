package com.gxuwz.beethoven.page.index.videoview.mv;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.video.VideoBoxAdapter;
import com.gxuwz.beethoven.adapter.video.mv.ChoiceMvAdapter;
import com.gxuwz.beethoven.model.entity.video.VideoBox;
import com.gxuwz.beethoven.model.entity.video.mv.ChoiceMv;
import com.gxuwz.beethoven.page.index.videoview.VideoBoxItem;

import java.util.ArrayList;
import java.util.List;

/**
 * mv 子页
 */
public class MvVideoInit {

    View mvVideoView;
    List<VideoBox> videoBoxList;
    List<ChoiceMv> choiceMvList;
    RecyclerView choiceMv,wonderfulMv;

    public MvVideoInit(View mvVideoView) {
        this.mvVideoView = mvVideoView;
    }

    public void init(Context context){
        findByIdAndNew(context);
    }

    public void findByIdAndNew(Context context){
        videoBoxList = new ArrayList<VideoBox>();
        choiceMvList = new ArrayList<ChoiceMv>();
        setData();
        choiceMv = mvVideoView.findViewById(R.id.choice_mv);
        choiceMv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        choiceMv.setAdapter(new ChoiceMvAdapter(choiceMvList,context));

        wonderfulMv = mvVideoView.findViewById(R.id.wonderful_mv);
        VideoBoxItem videoBoxItem = new VideoBoxItem(wonderfulMv,videoBoxList);
        videoBoxItem.init(context);
    }

    public void setData(){
        ChoiceMv choiceMv = new ChoiceMv();
        choiceMv.setDiagonal("youth");
        choiceMv.setPlayNumber(10071);
        choiceMv.setContent("王牌冤家");
        choiceMv.setUsername("zhouriyue");
        choiceMvList.add(choiceMv);

        choiceMv = new ChoiceMv();
        choiceMv.setDiagonal("youth");
        choiceMv.setPlayNumber(10071);
        choiceMv.setContent("王牌冤家");
        choiceMv.setUsername("zhouriyue");
        choiceMvList.add(choiceMv);

        choiceMv = new ChoiceMv();
        choiceMv.setDiagonal("youth");
        choiceMv.setPlayNumber(10071);
        choiceMv.setContent("王牌冤家");
        choiceMv.setUsername("zhouriyue");
        choiceMvList.add(choiceMv);

        choiceMv = new ChoiceMv();
        choiceMv.setDiagonal("youth");
        choiceMv.setPlayNumber(10071);
        choiceMv.setContent("王牌冤家");
        choiceMv.setUsername("zhouriyue");
        choiceMvList.add(choiceMv);

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

        videoBox = new VideoBox();
        videoBox.setDiagonal("youth");
        videoBox.setGetLike(23);
        videoBox.setPlayNumber(21345);
        videoBox.setPlayTime("12:00");
        videoBox.setMessNumber(25);
        videoBox.setTag("#人气榜飙升榜#");
        videoBox.setTitle("周深《爱若琉璃》混剪 电视剧 《琉璃》 主题曲");
        videoBoxList.add(videoBox);
    }
}
