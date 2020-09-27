package com.gxuwz.beethoven.model.entity.mlog;

import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.mlog.Mlog;

import java.util.List;

public class ImageWordMlog extends Mlog {
    /**
     * 图片
     */
    private List<String> imageList;
    /**
     * 音乐海报
     */
    private String musicDiagonal;
    /**
     * 内容
     */
    private String content;
    /**
     * 背景音乐
     */
    private String backgroundMusic;
    /**
     * 语音
     */
    private String voice;

    /**
     * 点赞数
     */
    private Integer likeNumber;

    /**
     * 标题
     */
    private String title;

    private SysUser sysUser;

    public ImageWordMlog() {
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getMusicDiagonal() {
        return musicDiagonal;
    }

    public void setMusicDiagonal(String musicDiagonal) {
        this.musicDiagonal = musicDiagonal;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBackgroundMusic() {
        return backgroundMusic;
    }

    public void setBackgroundMusic(String backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
