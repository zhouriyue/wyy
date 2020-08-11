package com.gxuwz.beethoven.model.entity.dynamics;

import java.util.List;

/**
 * 视频动态
 */
public class VideoDynamics extends Dynamics{
    /**
     * 视频
     */
    private String video;
    /**
     * 内容
     */
    private String content;
    /**
     * 标题
     */
    private String title;
    /**
     * 标签
     */
    private List<String> label;
    /**
     * 背景音乐
     */
    private String backgroundMusic;
    /**
     * 云圈
     */
    private String cloudCircle;

    public VideoDynamics() {
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public String getBackgroundMusic() {
        return backgroundMusic;
    }

    public void setBackgroundMusic(String backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public String getCloudCircle() {
        return cloudCircle;
    }

    public void setCloudCircle(String cloudCircle) {
        this.cloudCircle = cloudCircle;
    }
}
