package com.gxuwz.beethoven.model.entity.find;

import com.gxuwz.beethoven.model.entity.my.follow.Singer;

public class Dic {
    /**
     * 海报
     */
    private String diagonal;
    private String name;
    private String detail;
    private Singer singer;

    public Dic() {
    }

    public String getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
}
