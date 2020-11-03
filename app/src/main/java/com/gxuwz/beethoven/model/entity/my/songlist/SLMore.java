package com.gxuwz.beethoven.model.entity.my.songlist;

public class SLMore {

    private String icon;
    private String name;
    /**
     * type 3表示下载
     */
    private int type;

    public SLMore() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
