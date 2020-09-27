package com.gxuwz.beethoven.model.entity.find.talk;

import com.gxuwz.beethoven.model.entity.SysUser;

import java.util.List;

/**
 * 图文说说实体
 * image-text talk entity
 */
public class ITTalk {

    private List<String> images;
    private String title;
    private String content;
    private Integer shareNumber;
    private Integer messNumber;
    private Integer likeNumber;
    private List<Comment> commentList;
    private SysUser sysUser;

    public ITTalk() {
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(Integer shareNumber) {
        this.shareNumber = shareNumber;
    }

    public Integer getMessNumber() {
        return messNumber;
    }

    public void setMessNumber(Integer messNumber) {
        this.messNumber = messNumber;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
