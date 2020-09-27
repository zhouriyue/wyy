package com.gxuwz.beethoven.model.entity.find;

public class SpecialFun {

    private String title;
    private String icon;
    /**
     * everyrecomm:每日推荐
     * slplaza:歌单
     * rankinglist:排行榜
     * sradiostation:电台
     * stelecast:直播
     */
    private String path;

    public SpecialFun() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
