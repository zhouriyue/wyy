package com.gxuwz.beethoven.model.entity;

import java.util.List;

/**
 * 热歌风向标
 */
public class HotVane {
    private String classType;
    private String color;
    private List<WindMusic> windMusic;
    private String diagonal;

    public HotVane() {
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<WindMusic> getWindMusic() {
        return windMusic;
    }

    public void setWindMusic(List<WindMusic> windMusic) {
        this.windMusic = windMusic;
    }

    public String getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }
}
