package com.gxuwz.beethoven.model.entity;

import com.gxuwz.beethoven.model.entity.current.SysUser;

import java.util.List;

public class MoreFun {

    private String title;

    private List<Fun> funList;

    private SysUser sysUser;

    private int type;

    public MoreFun() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Fun> getFunList() {
        return funList;
    }

    public void setFunList(List<Fun> funList) {
        this.funList = funList;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
