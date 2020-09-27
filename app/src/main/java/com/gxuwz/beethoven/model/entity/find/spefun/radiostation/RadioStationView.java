package com.gxuwz.beethoven.model.entity.find.spefun.radiostation;

import com.gxuwz.beethoven.model.entity.find.Banners;

import java.util.List;

public class RadioStationView {

    /**
     * 轮播图  banner
     */
    private Banners banners;

    /**
     * 电台个性功能 radio station specail funation
     */
    private List<RadioStationSF> radioStationSFList;

    /**
     * 电台列表 radio station list
     */
    private List<RadioStation> radioStationList;

    /**
     * 电台类型 radio station class
     */
    private List<StationsClass> stationsClassList;

    /**
     * 界面显示风格  UI show type
     * showType:
     *           1.表示轮播图风格。banner
     *           2.表示电台个性功能。 radio station type
     *           3.表示电台列表。 The radio stations is hive title
     *           4.表示电台精品显示标题。 The radio stations is show title and price
     *           5.表示电台精品列表显示。 List show the radio stations
     *           6.表示电台分类显示。 The show is radio station classs
     */
    private int showType;

    /**
     * 是否显示价格
     * Does it show the price
     * 0 不显示价格 hive price
     * 1 显示价格  show price
     */
    private int isShowPrice;

    /**
     * 标题 title
     */
    private String title;

    /**
     * 标题上的icon  icon of  exist title
     */
    private String titleIcon;

    /**
     * 小标题 radio station subTitle
     */
    private String subTitle;

    /**
     * 右边按钮内容 right buttom content
     */
    private String tagTitle;

    /**
     * 右边按钮图片 right buttom icon
     */
    private String tagIcon;

    /**
     * 背景图片 background image
     */
    private String image;

    public RadioStationView() {
    }

    public Banners getBanners() {
        return banners;
    }

    public void setBanners(Banners banners) {
        this.banners = banners;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public List<RadioStationSF> getRadioStationSFList() {
        return radioStationSFList;
    }

    public void setRadioStationSFList(List<RadioStationSF> radioStationSFList) {
        this.radioStationSFList = radioStationSFList;
    }

    public List<RadioStation> getRadioStationList() {
        return radioStationList;
    }

    public void setRadioStationList(List<RadioStation> radioStationList) {
        this.radioStationList = radioStationList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTagIcon() {
        return tagIcon;
    }

    public void setTagIcon(String tagIcon) {
        this.tagIcon = tagIcon;
    }

    public String getTitleIcon() {
        return titleIcon;
    }

    public void setTitleIcon(String titleIcon) {
        this.titleIcon = titleIcon;
    }

    public int getIsShowPrice() {
        return isShowPrice;
    }

    public void setIsShowPrice(int isShowPrice) {
        this.isShowPrice = isShowPrice;
    }

    public List<StationsClass> getStationsClassList() {
        return stationsClassList;
    }

    public void setStationsClassList(List<StationsClass> stationsClassList) {
        this.stationsClassList = stationsClassList;
    }
}
