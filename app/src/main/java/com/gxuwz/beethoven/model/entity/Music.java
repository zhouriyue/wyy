package com.gxuwz.beethoven.model.entity;

public class Music {

    private String musicId;
    private String musicName;
    private String singer;
    private Integer duration;
    private String songUrl;
    private String songPicUrl;
    private Integer isHaveMv;
    private String muUrl;
    private String recommend;
    private String reallyId;

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSongPicUrl() {
        return songPicUrl;
    }

    public void setSongPicUrl(String songPicUrl) {
        this.songPicUrl = songPicUrl;
    }

    public Integer getIsHaveMv() {
        return isHaveMv;
    }

    public void setIsHaveMv(Integer isHaveMv) {
        this.isHaveMv = isHaveMv;
    }

    public String getMuUrl() {
        return muUrl;
    }

    public void setMuUrl(String muUrl) {
        this.muUrl = muUrl;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getReallyId() {
        return reallyId;
    }

    public void setReallyId(String reallyId) {
        this.reallyId = reallyId;
    }
}
