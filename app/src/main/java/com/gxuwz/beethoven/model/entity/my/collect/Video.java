package com.gxuwz.beethoven.model.entity.my.collect;

import android.content.Intent;

public class Video {
    private String diagonal;
    private Integer playNumber;
    private String videoName;
    private String uploadTime;
    private String uploadMan;

    public Video() {
    }

    public String getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadMan() {
        return uploadMan;
    }

    public void setUploadMan(String uploadMan) {
        this.uploadMan = uploadMan;
    }
}
