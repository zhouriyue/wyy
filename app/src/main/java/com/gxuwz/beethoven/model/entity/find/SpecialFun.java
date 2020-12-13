package com.gxuwz.beethoven.model.entity.find;

public class SpecialFun {

    /** 编号 */
    private Long id;
    private String title;
    private String icon;
    /**
     * everyrecomm:每日推荐
     * slplaza:歌单
     * rankinglist:排行榜
     */
    private String path;

    public SpecialFun() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
