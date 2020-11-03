package com.gxuwz.beethoven.model.entity.search;

import java.util.List;

public class Search {

    /**
     * 显示类型 show type
     * 1.图片 image
     * 2.搜索历史字符 This word is search history;
     */
    private int showType;
    /**
     * 项的标题 item title
     */
    private String title;
    /**
     * 标题字符 title word
     */
    private String tag;
    /**
     * 标签图标 tag icon
     */
    private String tagIcon;

    /**
     * 搜索历史字符
     * This list is search history word
     */
    private List<String> searchHistoryList;

    public Search() {
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagIcon() {
        return tagIcon;
    }

    public void setTagIcon(String tagIcon) {
        this.tagIcon = tagIcon;
    }

    public List<String> getSearchHistoryList() {
        return searchHistoryList;
    }

    public void setSearchHistoryList(List<String> searchHistoryList) {
        this.searchHistoryList = searchHistoryList;
    }
}
