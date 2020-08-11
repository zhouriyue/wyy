package com.gxuwz.beethoven.model.entity.dynamics;

import java.util.List;

/**
 * 图文动态
 */
public class ImageWordDynamics {
    /**
     * 动态
     */
    private String content;
    /**
     * 音乐
     */
    private String music;
    /**
     * 云圈
     */
    private String cloudCircle;
    /**
     * 图片
     */
    private List<String> images;
    /**
     * 好友
     */
    private String friend;

    public ImageWordDynamics() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getCloudCircle() {
        return cloudCircle;
    }

    public void setCloudCircle(String cloudCircle) {
        this.cloudCircle = cloudCircle;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }
}
