package com.gxuwz.beethoven.adapter.find;

public class TalkItem {
    /**
     * 点赞数
     */
    private Integer givelikeNumber;
    /**
     * 说说封面
     */
    private String diagonal;
    /**
     * 说说标题
     */
    private String talkTitle;

    public TalkItem() {
    }

    public Integer getGivelikeNumber() {
        return givelikeNumber;
    }

    public void setGivelikeNumber(Integer givelikeNumber) {
        this.givelikeNumber = givelikeNumber;
    }

    public String getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }

    public String getTalkTitle() {
        return talkTitle;
    }

    public void setTalkTitle(String talkTitle) {
        this.talkTitle = talkTitle;
    }
}
