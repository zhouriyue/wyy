package com.gxuwz.beethoven.model.entity.find.chatroom;

import com.gxuwz.beethoven.model.entity.SysUser;
import com.gxuwz.beethoven.model.entity.find.talk.Comment;

import java.util.List;

/**
 * 畅聊房间实体类
 */
public class ChatRoom {

    /**
     * 房主
     */
    private SysUser sysUser;
    /**
     * 房客
     */
    private List<SysUser> roomerList;
    private List<Comment> commentList;
    private String title;
    private Integer type;

    public ChatRoom() {
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public List<SysUser> getRoomerList() {
        return roomerList;
    }

    public void setRoomerList(List<SysUser> roomerList) {
        this.roomerList = roomerList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
