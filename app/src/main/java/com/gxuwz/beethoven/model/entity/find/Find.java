package com.gxuwz.beethoven.model.entity.find;

import com.gxuwz.beethoven.model.entity.Song;
import com.gxuwz.beethoven.model.entity.SongList;
import com.gxuwz.beethoven.model.entity.mlog.Mlog;

import java.util.List;

public class Find {
    /**
     *  type=
     *  1.轮播图
     *  2.个性功能
     *  3.推荐歌单
     *  4.推荐直播
     *  5.推荐歌曲
     *  6.音乐日历
     *  7.推荐MLog
     *  8.推荐MLog
     *  9.推荐房间
     *  10.推荐云圈
     *  11.推荐排行榜
     *  12.推荐个性歌曲
     */
    private String title;
    private String toMangy;
    private String icon;

    private int type;
    //轮播图
    private List<Banners> bannersList;
    //功能
    private List<SpecialFun> specialFunList;
    //歌单
    private List<SongList> songLists;
    //直播
    private List<Telecast> telecastList;
    //歌曲
    private List<Song> songList;
    //音乐日历
    private List<MusicCal> musicCals;
    //MLog
    private List<Mlog> mlogList;
    //碟
    private List<Dic> dicList;
    //房间
    private List<Room> roomList;
    //云圈
    private List<YunVillage> yunVillageList;
    //排行榜
    private List<RankingList> rankingListList;
    //个性音乐
    private List<SpeSong> speSongList;

    public Find() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Banners> getBannersList() {
        return bannersList;
    }

    public void setBannersList(List<Banners> bannersList) {
        this.bannersList = bannersList;
    }

    public List<SpecialFun> getSpecialFunList() {
        return specialFunList;
    }

    public void setSpecialFunList(List<SpecialFun> specialFunList) {
        this.specialFunList = specialFunList;
    }

    public List<SongList> getSongLists() {
        return songLists;
    }

    public void setSongLists(List<SongList> songLists) {
        this.songLists = songLists;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public String getToMangy() {
        return toMangy;
    }

    public void setToMangy(String toMangy) {
        this.toMangy = toMangy;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Telecast> getTelecastList() {
        return telecastList;
    }

    public void setTelecastList(List<Telecast> telecastList) {
        this.telecastList = telecastList;
    }

    public List<MusicCal> getMusicCals() {
        return musicCals;
    }

    public void setMusicCals(List<MusicCal> musicCals) {
        this.musicCals = musicCals;
    }

    public List<Mlog> getMlogList() {
        return mlogList;
    }

    public void setMlogList(List<Mlog> mlogList) {
        this.mlogList = mlogList;
    }

    public List<Dic> getDicList() {
        return dicList;
    }

    public void setDicList(List<Dic> dicList) {
        this.dicList = dicList;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public List<YunVillage> getYunVillageList() {
        return yunVillageList;
    }

    public void setYunVillageList(List<YunVillage> yunVillageList) {
        this.yunVillageList = yunVillageList;
    }

    public List<RankingList> getRankingListList() {
        return rankingListList;
    }

    public void setRankingListList(List<RankingList> rankingListList) {
        this.rankingListList = rankingListList;
    }

    public List<SpeSong> getSpeSongList() {
        return speSongList;
    }

    public void setSpeSongList(List<SpeSong> speSongList) {
        this.speSongList = speSongList;
    }
}
