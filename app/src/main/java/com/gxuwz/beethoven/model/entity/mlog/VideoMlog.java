package com.gxuwz.beethoven.model.entity.mlog;

import com.gxuwz.beethoven.model.entity.mlog.Mlog;

public class VideoMlog extends Mlog {
    /**
     * 内容
     */
    private String content;
    /**
     * 主题
     */
    private String theme;
    /**
     * 地图
     */
    private String map;
    /**
     * 视频地址
     */
    private String videoPath;
    /**
     * 封面
     */
    private String diagonal;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }
}
