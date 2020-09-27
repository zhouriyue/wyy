package com.gxuwz.beethoven.model.entity.find.spefun.telecast;

import com.gxuwz.beethoven.model.entity.find.Telecast;

import java.util.List;

/**
 * 用户关注的直播
 * Live broadcast of user concerns
 */
public class TelecastFollowView {

    /**
     * 直播实体类列表
     * The list is telecast entity
     */
    private List<Telecast> telecastList;

    /**
     * 标题 title
     */
    private String title;

    /**
     * 显示方式 display mode
     * 1.显示用户关注的直播。 This telecast is user follow
     * 2.显示推荐的直播。This telecast is recommend
     */
    private Integer showType;

    public TelecastFollowView() {
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

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }
}
