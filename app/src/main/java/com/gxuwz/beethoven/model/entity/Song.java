package com.gxuwz.beethoven.model.entity;

/**
 * 歌曲类
 */
public class Song {

    /**
     * 歌手
     */
    private String singer;
    /**
     * 歌曲名
     */
    private String songName;
    /**
     * 歌曲的地址
     */
    private String path;
    /**
     * 歌曲长度
     */
    private int duration;
    /**
     * 歌曲的大小
     */
    private long size;

    /**
     * 图片
     */
    private String image;

    /**
     *详情
     */
    private String detail;

    /**
     * 是否为新歌
     */
    private Integer isNew;

    public Song() {
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }
}
