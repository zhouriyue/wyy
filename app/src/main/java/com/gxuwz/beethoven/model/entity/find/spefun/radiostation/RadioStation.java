package com.gxuwz.beethoven.model.entity.find.spefun.radiostation;

import com.gxuwz.beethoven.model.entity.SysUser;

/**
 * 电台实体类 radio station entity
 */
public class RadioStation {

    private String img;
    private String name;
    private String type;

    /**
     * 电台的标题
     * Title of the radio stations
     */
    private String title;
    /**
     * 备注
     */
    private String remark;

    /**
     * 0 表示非付费 not pay
     * 1 表示付费 pay
     */
    private Integer isPay;

    /**
     * 费用 pay radio station need cost
     */
    private double cost;

    /**
     * 热度 hot
     * 通过搜索量，播放量，转发，评论计算得到
     * The heat is calculated by the number of comments and the amount of playback
     */
    private double hotNumber;

    /**
     * 创建电台的用户
     * Users who create radio stations
     */
    private SysUser sysUser;

    /**
     * 是否显示价格
     */
    private boolean isShowPrice;

    public RadioStation() {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getHotNumber() {
        return hotNumber;
    }

    public void setHotNumber(double hotNumber) {
        this.hotNumber = hotNumber;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShowPrice() {
        return isShowPrice;
    }

    public void setShowPrice(boolean showPrice) {
        isShowPrice = showPrice;
    }
}
