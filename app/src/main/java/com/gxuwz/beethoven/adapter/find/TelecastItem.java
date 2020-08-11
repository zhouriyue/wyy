package com.gxuwz.beethoven.adapter.find;

/**
 * 直播
 */
public class TelecastItem {
    private Integer onLineNumber;
    private String telecastDisgonal;
    private String type;
    private String title;

    /**
     * Type:"1"表示尬聊."2"表示打碟."3"表示声控
     * @return
     */

    public TelecastItem() {
    }

    public Integer getOnLineNumber() {
        return onLineNumber;
    }

    public void setOnLineNumber(Integer onLineNumber) {
        this.onLineNumber = onLineNumber;
    }

    public String getTelecastDisgonal() {
        return telecastDisgonal;
    }

    public void setTelecastDisgonal(String telecastDisgonal) {
        this.telecastDisgonal = telecastDisgonal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
