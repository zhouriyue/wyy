package com.gxuwz.beethoven.model.entity.find.talk;

import com.gxuwz.beethoven.model.entity.SysUser;

public class Comment {
    /**
     * 评论的用户
     */
    private SysUser sysUser;
    private String content;

    public Comment() {
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
