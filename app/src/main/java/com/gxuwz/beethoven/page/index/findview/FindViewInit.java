package com.gxuwz.beethoven.page.index.findview;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.RequiresApi;

public class FindViewInit {
    View FindView;

    /**
     * 轮播图
     */
    BannerInit findBannerInit;
    /**
     * 每日菜单
     */
    FindMenuGlideInit findMenuGlideInit;
    /**
     * 精选歌单
     */
    FindChoiceSongListInit findChoiceSongListInit;
    /**
     * 相似歌曲
     */
    FindRecommendLikeInit findRecommendLikeInit;
    /**
     * “爱上音乐为伴的下午“模块
     */
    FindLikeAfternoonInit findLikeAfternoonInit;
    /**
     * “精选说说”模块
     */
    FindTalkInit findTalkInit;
    /**
     * “精选直播”模块
     */
    FindTelecastInit findTelecastInit;
    /**
     * “新歌和新碟”模块
     */
    FindNewSongDisc findNewSongDisc;
    /**
     * “精选房间”模块
     */
    FindChoiceRoomInit findChoiceRoomInit;
    /**
     * “星座推荐”模块
     */
    FindConstellationInit constellationInit;
    /**
     * “云圈”模块
     */
    FindCloudCircle findCloudCircle;
    /**
     * “风向标”模块
     */
    FindHotVane findHotVane;

    WindowManager windowManager;
    Context context;

    public FindViewInit(View findView, WindowManager windowManager, Context context) {
        this.FindView = findView;
        this.windowManager = windowManager;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init(LayoutInflater layoutInflater){
        findByIdAndNew();
        //设置图片加载器

        /**
         * 初始轮播图
         */
        findBannerInit.init();
        /**
         * 每日菜单
         */
        findMenuGlideInit.init(layoutInflater,windowManager,context);
        /**
         * 精选歌单
         */
        findChoiceSongListInit.init(layoutInflater,windowManager,context);
        /**
         * 相似歌曲
         */
        findRecommendLikeInit.init(layoutInflater,windowManager,context);
        /**
         * “爱上音乐为伴的下午“模块
         */
        findLikeAfternoonInit.init(layoutInflater,windowManager,context);
        /**
         * “精选说说”模块
         */
        findTalkInit.init(layoutInflater,windowManager,context);
        /**
         * “精选直播”模块
         */
        findTelecastInit.init(layoutInflater,windowManager,context);
        /**
         * “新歌和新碟”模块
         */
        findNewSongDisc.init(layoutInflater,windowManager,context);
        /**
         * “精选房间”模块
         */
        findChoiceRoomInit.init(layoutInflater,windowManager,context);
        /**
         * “星座推荐”模块
         */
        constellationInit.init(layoutInflater,windowManager,context);
        /**
         * “云圈”模块
         */
        findCloudCircle.init(layoutInflater,windowManager,context);
        /**
         * “风向标”模块
         */
        findHotVane.init(layoutInflater,windowManager,context);
    }



    public void findByIdAndNew(){
        findBannerInit = new BannerInit(FindView);
        findMenuGlideInit = new FindMenuGlideInit(FindView);
        findChoiceSongListInit = new FindChoiceSongListInit(FindView);
        findRecommendLikeInit = new FindRecommendLikeInit(FindView);
        findLikeAfternoonInit = new FindLikeAfternoonInit(FindView);
        findTalkInit = new FindTalkInit(FindView);
        findTelecastInit = new FindTelecastInit(FindView);
        findNewSongDisc = new FindNewSongDisc(FindView);
        findChoiceRoomInit = new FindChoiceRoomInit(FindView);
        constellationInit = new FindConstellationInit(FindView);
        findCloudCircle = new FindCloudCircle(FindView);
        findHotVane = new FindHotVane(FindView);
    }
}
