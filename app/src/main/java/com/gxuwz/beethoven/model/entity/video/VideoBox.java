package com.gxuwz.beethoven.model.entity.video;

public class VideoBox {
    private String tag;
    private Integer playNumber;
    private String playTime;
    private String title;
    private Integer getLike;
    private Integer messNumber;
    private String diagonal;

    public VideoBox() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGetLike() {
        return getLike;
    }

    public void setGetLike(Integer getLike) {
        this.getLike = getLike;
    }

    public Integer getMessNumber() {
        return messNumber;
    }

    public void setMessNumber(Integer messNumber) {
        this.messNumber = messNumber;
    }

    public String getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }
}
