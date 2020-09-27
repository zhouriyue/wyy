package com.gxuwz.beethoven.model.entity.find.spefun.telecast;

import com.gxuwz.beethoven.model.entity.SysUser;

public class TrMessage {

    private SysUser sysUser;
    private String content;

    public TrMessage() {
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
