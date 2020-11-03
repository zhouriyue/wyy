package com.gxuwz.beethoven.page.index.findview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.gxuwz.beethoven.R;
import com.gxuwz.beethoven.adapter.find.FindAdapter;
import com.gxuwz.beethoven.model.entity.Song;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.find.Banners;
import com.gxuwz.beethoven.model.entity.find.Dic;
import com.gxuwz.beethoven.model.entity.find.Find;
import com.gxuwz.beethoven.model.entity.find.MusicCal;
import com.gxuwz.beethoven.model.entity.find.RankingList;
import com.gxuwz.beethoven.model.entity.find.Room;
import com.gxuwz.beethoven.model.entity.find.SpeSong;
import com.gxuwz.beethoven.model.entity.find.SpecialFun;
import com.gxuwz.beethoven.model.entity.find.Telecast;
import com.gxuwz.beethoven.model.entity.find.YunVillage;
import com.gxuwz.beethoven.model.entity.mlog.ImageWordMlog;
import com.gxuwz.beethoven.model.entity.mlog.Mlog;
import com.gxuwz.beethoven.model.entity.my.follow.Singer;
import java.util.ArrayList;
import java.util.List;

public class FindViewInit {

    View findView;
    RecyclerView findViewRv;
    
    WindowManager windowManager;
    Context context;
    List<Find> findList;

    public FindViewInit(View findView, WindowManager windowManager, Context context) {
        this.findView = findView;
        this.windowManager = windowManager;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init(LayoutInflater layoutInflater){
        findByIdAndNew();
    }



    public void findByIdAndNew(){
        findViewRv = findView.findViewById(R.id.find_view_rv);
        findList = new ArrayList<Find>();
        setData();

        /**
         * recycler 设置流畅度
         */
        findViewRv.setHasFixedSize(true);
        findViewRv.setNestedScrollingEnabled(false);
        findViewRv.setItemViewCacheSize(15);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        findViewRv.setRecycledViewPool(recycledViewPool);
        findViewRv.setOnScrollListener(new RecyclerView.OnScrollListener(){
            //用来标记是否正在向最后一个滑动，既是否向下滑动
            boolean isSlidingToLast = false;

            @SuppressLint("WrongConstant")
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int[] lastVisiblePositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
                    int lastVisiblePos = getMaxElem(lastVisiblePositions);
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部
                    if (lastVisiblePos == (totalItemCount -1) && isSlidingToLast) {
                        //加载更多功能的代码
                        Toast.makeText(context,"加载更多",1).show();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if(dy > 0){
                    //大于0表示，正在向下滚动
                    isSlidingToLast = true;
                }else{
                    //小于等于0 表示停止或向上滚动
                    isSlidingToLast = false;
                }

            }
        });
        findViewRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        FindAdapter findAdapter = new FindAdapter(context,findList);
        findViewRv.setAdapter(findAdapter);
    }

    private void setData(){
        /**
         * 轮播图
         */
        Find find = new Find();
        find.setType(1);
        List<Banners> bannersList = new ArrayList<Banners>();
        Banners banners = new Banners();
        List<String> images = new ArrayList<>();
        images.add("http://kwimg2.kuwo.cn/star/upload/66/85/1575256374021_.jpg");
        images.add("http://kwimg2.kuwo.cn/star/upload/71/68/1575818166158_.jpg");
        images.add("http://kwimg1.kuwo.cn/star/upload/68/54/1575429173078_.jpg");
        banners.setImages(images);
        banners.setType("独家");
        banners.setPath("www.baidu.com");
        bannersList.add(banners);
        find.setBannersList(bannersList);
        findList.add(find);
        /**
         * 个性功能
         */
        find = new Find();
        find.setType(2);
        List<SpecialFun> specialFunList = new ArrayList<SpecialFun>();

        SpecialFun specialFun = new SpecialFun();
        specialFun.setIcon("youth");
        specialFun.setTitle("每日推荐");
        specialFun.setPath("everyrecomm");
        specialFunList.add(specialFun);

        specialFun = new SpecialFun();
        specialFun.setIcon("zhoushen");
        specialFun.setTitle("歌单");
        specialFun.setPath("slplaza");
        specialFunList.add(specialFun);

        specialFun = new SpecialFun();
        specialFun.setIcon("youth");
        specialFun.setTitle("排行榜");
        specialFun.setPath("rankinglist");
        specialFunList.add(specialFun);

        specialFun = new SpecialFun();
        specialFun.setIcon("youth");
        specialFun.setTitle("电台");
        specialFun.setPath("sradiostation");
        specialFunList.add(specialFun);

        specialFun = new SpecialFun();
        specialFun.setIcon("youth");
        specialFun.setTitle("直播");
        specialFun.setPath("stelecast");
        specialFunList.add(specialFun);

        find.setSpecialFunList(specialFunList);
        findList.add(find);

        /**
         * 推荐歌单
         */
        find = new Find();
        find.setTitle("人气歌单推荐");
        find.setToMangy("查看更多");
        find.setIcon("play");
        find.setType(3);
        List<SongList> songLists = new ArrayList<SongList>();

        SongList songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("今天从《骄傲的少年》听起私人雷达，今天从《骄傲的少年》听起私人雷达");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("今天从《骄傲的少年》听起私人雷达，今天从《骄傲的少年》听起私人雷达");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("今天从《骄傲的少年》听起私人雷达，今天从《骄傲的少年》听起私人雷达");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("今天从《骄傲的少年》听起私人雷达，今天从《骄傲的少年》听起私人雷达");
        songLists.add(songList);

        find.setSongLists(songLists);
        findList.add(find);

        /**
         * 推荐直播
         */
        find = new Find();
        find.setTitle("嘿！你又在听我说嘛？");
        find.setToMangy("查看更多");
        find.setIcon("play");
        find.setType(4);
        List<Telecast> telecastList = new ArrayList<Telecast>();

        Telecast telecast = new Telecast();
        telecast.setImg("youth");
        telecast.setType("声控");
        telecast.setOnlineNumber(1091);
        telecast.setTitle("小陈是声控主播谢谢");
        telecastList.add(telecast);

        telecast = new Telecast();
        telecast.setImg("zhoushen");
        telecast.setType("元气");
        telecast.setOnlineNumber(1091);
        telecast.setTitle("小陈是声控主播谢谢");
        telecastList.add(telecast);

        telecast = new Telecast();
        telecast.setImg("zhoushen");
        telecast.setType("声控");
        telecast.setOnlineNumber(1091);
        telecast.setTitle("小陈是声控主播谢谢");
        telecastList.add(telecast);

        telecast = new Telecast();
        telecast.setImg("zhoushen");
        telecast.setType("声控");
        telecast.setOnlineNumber(1091);
        telecast.setTitle("小陈是声控主播谢谢");
        telecastList.add(telecast);

        find.setTelecastList(telecastList);
        findList.add(find);

        /**
         * 推荐歌曲
         */
        find = new Find();
        find.setTitle("根据任贤齐推荐");
        find.setToMangy("查看更多");
        find.setIcon("play");
        find.setType(5);
        List<Song> recSongList = new ArrayList<Song>();

        Song song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("少年游");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("风云无阻");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        find.setSongList(recSongList);
        findList.add(find);

        /**
         * 音乐日历
         */
        find = new Find();
        find.setType(6);
        List<MusicCal> musicCalList = new ArrayList<MusicCal>();
        MusicCal musicCal = new MusicCal();
        musicCal.setImage("zhoushen");
        musicCal.setTime("今天");
        musicCal.setTitle("福克斯「生来如此」首专开箱LIVE，福克斯「生来如此」首专开箱LIVE，福克斯「生来如此」首专开箱LIVE");
        musicCalList.add(musicCal);

        musicCal = new MusicCal();
        musicCal.setImage("zhoushen1");
        musicCal.setTime("明天");
        musicCal.setTitle("预告——R1SE预告——R1SE预告——R1SE预告——R1SE预告——R1SE预告——R1SE预告——R1SE预告——R1SE");
        musicCalList.add(musicCal);

        musicCal = new MusicCal();
        musicCal.setImage("zhoushen");
        musicCal.setTime("今天");
        musicCal.setTitle("陈瑶$丁爽等的专辑「穿盔甲的少女原声OST」,陈瑶$丁爽等的专辑「穿盔甲的少女原声OST」");
        musicCalList.add(musicCal);

        musicCal = new MusicCal();
        musicCal.setImage("zhoushen1");
        musicCal.setTime("今天");
        musicCal.setTitle("福克斯「生来如此」首专开箱LIVE,福克斯「生来如此」首专开箱LIVE,福克斯「生来如此」首专开箱LIVE");
        musicCalList.add(musicCal);
        find.setMusicCals(musicCalList);

        findList.add(find);

        /**
         * 官方推荐歌单
         */
        find = new Find();
        find.setTitle("忙碌暂停 音乐Play>>");
        find.setToMangy("查看更多");
        find.setIcon("play");
        find.setType(3);
        songLists = new ArrayList<SongList>();

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("全天候热舞现场 随电音脉动一起舞蹈");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("最懂你的华语推荐每日更新35首");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("值得你红心收藏的女声");
        songLists.add(songList);

        songList = new SongList();
        songList.setSongListUrl("youth");
        songList.setPlayNumber(2000);
        songList.setSongListName("沉入后摇海洋 与情绪共舞");
        songLists.add(songList);

        find.setSongLists(songLists);
        findList.add(find);

        /**
         * 推荐mlog
         */
        find = new Find();
        find.setTitle("周深：一个人的唱诗班");
        find.setToMangy("查看更多");
        find.setIcon("play");
        find.setType(7);
        List<Mlog> mlogList = new ArrayList<Mlog>();

        ImageWordMlog imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("zhoushen1");
        imageWordMlog.setLikeNumber(1200);
        imageWordMlog.setTitle("#我的中学时代不就是他们吗，我的中学时代不就是他们吗，我的中学时代不就是他们吗，#我的中学时代不就是他们吗");
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("zhoushen1");
        imageWordMlog.setLikeNumber(1200);
        imageWordMlog.setTitle("#我的中学时代不就是他们吗，我的中学时代不就是他们吗，我的中学时代不就是他们吗，#我的中学时代不就是他们吗");
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("zhoushen1");
        imageWordMlog.setLikeNumber(1200);
        imageWordMlog.setTitle("#我的中学时代不就是他们吗，我的中学时代不就是他们吗，我的中学时代不就是他们吗，#我的中学时代不就是他们吗");
        mlogList.add(imageWordMlog);

        imageWordMlog = new ImageWordMlog();
        imageWordMlog.setMusicDiagonal("zhoushen1");
        imageWordMlog.setLikeNumber(1200);
        imageWordMlog.setTitle("#我的中学时代不就是他们吗，我的中学时代不就是他们吗，我的中学时代不就是他们吗，#我的中学时代不就是他们吗");
        mlogList.add(imageWordMlog);

        find.setMlogList(mlogList);
        findList.add(find);

        /**
         * 新歌和新碟
         */
        find = new Find();
        find.setType(8);
        recSongList = new ArrayList<Song>();

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("少年游");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("风云无阻");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        find.setSongList(recSongList);

        List<Dic> dicList = new ArrayList<Dic>();
        Dic dic = new Dic();
        dic.setDetail("无名狂 电影原声碟");
        dic.setDiagonal("zhoushen");
        Singer singer = new Singer();
        singer.setSingerName("任贤齐");
        dic.setSinger(singer);
        dicList.add(dic);

        dic = new Dic();
        dic.setDetail("起卖猛涨 电影原声大碟");
        dic.setDiagonal("zhoushen");
        singer = new Singer();
        singer.setSingerName("群星");
        dic.setSinger(singer);
        dicList.add(dic);

        dic = new Dic();
        dic.setDetail("起卖猛涨 电影原声大碟");
        dic.setDiagonal("zhoushen");
        singer = new Singer();
        singer.setSingerName("群星");
        dic.setSinger(singer);
        dicList.add(dic);

        dic = new Dic();
        dic.setDetail("起卖猛涨 电影原声大碟");
        dic.setDiagonal("zhoushen");
        singer = new Singer();
        singer.setSingerName("群星");
        dic.setSinger(singer);
        dicList.add(dic);

        dic = new Dic();
        dic.setDetail("起卖猛涨 电影原声大碟");
        dic.setDiagonal("zhoushen");
        singer = new Singer();
        singer.setSingerName("群星");
        dic.setSinger(singer);
        dicList.add(dic);

        dic = new Dic();
        dic.setDetail("起卖猛涨 电影原声大碟");
        dic.setDiagonal("zhoushen");
        singer = new Singer();
        singer.setSingerName("群星");
        dic.setSinger(singer);
        dicList.add(dic);

        dic = new Dic();
        dic.setDetail("起卖猛涨 电影原声大碟");
        dic.setDiagonal("zhoushen");
        singer = new Singer();
        singer.setSingerName("群星");
        dic.setSinger(singer);
        dicList.add(dic);

        dic = new Dic();
        dic.setDetail("起卖猛涨 电影原声大碟");
        dic.setDiagonal("zhoushen");
        singer = new Singer();
        singer.setSingerName("群星");
        dic.setSinger(singer);
        dicList.add(dic);

        dic = new Dic();
        dic.setDetail("起卖猛涨 电影原声大碟");
        dic.setDiagonal("zhoushen");
        singer = new Singer();
        singer.setSingerName("群星");
        dic.setSinger(singer);
        dicList.add(dic);

        find.setDicList(dicList);
        findList.add(find);

        /**
         * 推荐房间
         */
        find = new Find();
        find.setTitle("蹲着一个唱歌好听的小姐姐");
        find.setToMangy("查看更多");
        find.setIcon("play");
        find.setType(9);

        List<Room> roomList = new ArrayList<Room>();
        Room room = new Room();
        room.setRoomImg("youth");
        room.setType("唱歌听歌");
        room.setTitle("私人FM");
        List<SysUser> sysUserList = new ArrayList<SysUser>();
        SysUser sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("youth");
        sysUserList.add(sysUser);
        room.setOnlineSysUser(sysUserList);
        roomList.add(room);

        room = new Room();
        room.setRoomImg("youth");
        room.setType("恋爱心事");
        room.setTitle("解忧杂货店 |门诡|解忧杂货店 |门诡|解忧杂货店 |门诡|");
        sysUserList = new ArrayList<SysUser>();
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("youth");
        sysUserList.add(sysUser);
        room.setOnlineSysUser(sysUserList);
        roomList.add(room);

        room = new Room();
        room.setRoomImg("zhoushen");
        room.setType("唱歌听歌");
        room.setTitle("唱一首歌送一个对象，唱一首歌送一个对象");
        sysUserList = new ArrayList<SysUser>();
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("youth");
        sysUserList.add(sysUser);
        room.setOnlineSysUser(sysUserList);
        roomList.add(room);

        room = new Room();
        room.setRoomImg("zhoushen1");
        room.setType("唱歌听歌");
        room.setTitle("私人FM");
        sysUserList = new ArrayList<SysUser>();
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("zhoushen1");
        sysUserList.add(sysUser);
        sysUser = new SysUser();
        sysUser.setPerPic("youth");
        sysUserList.add(sysUser);
        room.setOnlineSysUser(sysUserList);
        roomList.add(room);
        find.setRoomList(roomList);
        findList.add(find);

        /**
         * 推荐歌曲
         */
        find = new Find();
        find.setTitle("80后 这里有你的青春");
        find.setToMangy("播放全部");
        find.setIcon("play");
        find.setType(5);
        recSongList = new ArrayList<Song>();

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("少年游");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("风云无阻");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        find.setSongList(recSongList);
        findList.add(find);

        /**
         * 推荐歌曲
         */
        find = new Find();
        find.setTitle("80后 这里有你的青春");
        find.setToMangy("播放全部");
        find.setIcon("play");
        find.setType(5);
        recSongList = new ArrayList<Song>();

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("少年游");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("风云无阻");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        find.setSongList(recSongList);
        findList.add(find);
        /**
         * 推荐歌曲
         */
        find = new Find();
        find.setTitle("80后 这里有你的青春");
        find.setToMangy("播放全部");
        find.setIcon("play");
        find.setType(5);
        recSongList = new ArrayList<Song>();

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("少年游");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("风云无阻");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        find.setSongList(recSongList);
        findList.add(find);
        /**
         * 推荐歌曲
         */
        find = new Find();
        find.setTitle("80后 这里有你的青春");
        find.setToMangy("播放全部");
        find.setIcon("play");
        find.setType(5);
        recSongList = new ArrayList<Song>();

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("少年游");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("风云无阻");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        song = new Song();
        song.setImage("youth");
        song.setDetail("给我一杯酒烽火几时休");
        song.setSinger("-任贤齐/周华健");
        song.setSongName("别傻了");
        recSongList.add(song);

        find.setSongList(recSongList);
        findList.add(find);

        /**
         * 推荐个性音乐
         */
        find = new Find();
        find.setTitle("别致翻唱 品味精华");
        find.setToMangy("查看更多");
        find.setIcon("play");
        find.setType(12);

        List<SpeSong> speSongList = new ArrayList<SpeSong>();
        SpeSong speSong = new SpeSong();
        speSong.setImage("youth");
        speSong.setName("陈东林翻唱填词陈东林翻唱填词");
        speSong.setTitle("古风小哥哥送你温暖歌声，古风小哥哥送你温暖歌声");
        speSongList.add(speSong);

        speSong = new SpeSong();
        speSong.setImage("youth");
        speSong.setName("陈东林翻唱填词陈东林翻唱填词");
        speSong.setTitle("古风小哥哥送你温暖歌声，古风小哥哥送你温暖歌声");
        speSongList.add(speSong);

        speSong = new SpeSong();
        speSong.setImage("youth");
        speSong.setName("陈东林翻唱填词陈东林翻唱填词");
        speSong.setTitle("古风小哥哥送你温暖歌声，古风小哥哥送你温暖歌声");
        speSongList.add(speSong);

        speSong = new SpeSong();
        speSong.setImage("youth");
        speSong.setName("陈东林翻唱填词陈东林翻唱填词");
        speSong.setTitle("古风小哥哥送你温暖歌声，古风小哥哥送你温暖歌声");
        speSongList.add(speSong);
        find.setSpeSongList(speSongList);

        findList.add(find);
    }

    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i]>maxVal)
                maxVal = arr[i];
        }
        return maxVal;
    }

}
