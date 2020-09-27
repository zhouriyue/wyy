package com.gxuwz.beethoven.model.entity.find;

import com.gxuwz.beethoven.model.entity.SysUser;

import java.util.List;

public class Room {

    private String roomImg;
    private String title;
    private String type;
    private List<SysUser> onlineSysUser;

    public Room() {
    }

    public String getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(String roomImg) {
        this.roomImg = roomImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SysUser> getOnlineSysUser() {
        return onlineSysUser;
    }

    public void setOnlineSysUser(List<SysUser> onlineSysUser) {
        this.onlineSysUser = onlineSysUser;
    }
}
