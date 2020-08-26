package com.gxuwz.beethoven.model.entity.my.follow;

import java.util.List;

public class Follow {
    private Singer singer;
    private String detail;
    private List<Song> song;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Follow() {
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public List<Song> getSong() {
        return song;
    }

    public void setSong(List<Song> song) {
        this.song = song;
    }
}
