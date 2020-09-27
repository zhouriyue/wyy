package com.gxuwz.beethoven.model.entity.find;

import com.gxuwz.beethoven.model.entity.SysUser;

/**
 * 直播实体类
 */
public class Telecast {

    private String type;
    private Integer onlineNumber;
    private String img;
    private String title;
    private String detail;
    private SysUser sysUser;
    private Integer hotNumber;

    public Telecast() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOnlineNumber() {
        return onlineNumber;
    }

    public void setOnlineNumber(Integer onlineNumber) {
        this.onlineNumber = onlineNumber;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getHotNumber() {
        return hotNumber;
    }

    public void setHotNumber(Integer hotNumber) {
        this.hotNumber = hotNumber;
    }
}
