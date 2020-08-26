package com.gxuwz.beethoven.model.entity.my.local.singer;

public class SingerItem {

    private String perPic;
    private String singerName;
    private Integer count;
    private Integer mvCount;
    private Integer albumCount;

    public SingerItem() {
    }

    public String getPerPic() {
        return perPic;
    }

    public void setPerPic(String perPic) {
        this.perPic = perPic;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getMvCount() {
        return mvCount;
    }

    public void setMvCount(Integer mvCount) {
        this.mvCount = mvCount;
    }

    public Integer getAlbumCount() {
        return albumCount;
    }

    public void setAlbumCount(Integer albumCount) {
        this.albumCount = albumCount;
    }
}
