package com.gxuwz.beethoven.model.entity.find.spefun.telecast;

import com.gxuwz.beethoven.model.entity.find.Banners;
import com.gxuwz.beethoven.model.entity.find.Telecast;

import java.util.List;

public class STelecastShow {

    private List<Telecast> telecastList;

    private Banners banners;

    /**
     * 标题
     */
    private String title;
    /**
     * 显示方式
     * 1.轮播图
     * 2.分类
     */
    private int showType;

    public STelecastShow() {
    }

    public List<Telecast> getTelecastList() {
        return telecastList;
    }

    public void setTelecastList(List<Telecast> telecastList) {
        this.telecastList = telecastList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public Banners getBanners() {
        return banners;
    }

    public void setBanners(Banners banners) {
        this.banners = banners;
    }
}
