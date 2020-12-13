package com.gxuwz.beethoven.model.entity.find.spefun.rankinglist;


import com.gxuwz.beethoven.model.entity.current.RankingList;

import java.util.List;

public class RankingListShow {

    private List<RankingList> rankingListList;

    /**
     * 1表示横着显示，2表示竖着显示
     */
    private int showType;

    private String title;

    public RankingListShow() {
    }

    public List<RankingList> getRankingListList() {
        return rankingListList;
    }

    public void setRankingListList(List<RankingList> rankingListList) {
        this.rankingListList = rankingListList;
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
}
