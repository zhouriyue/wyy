package com.gxuwz.beethoven.model.entity.my.local.album;

public class Album {
    private String albumDiagonal;
    private String albumTitle;
    private Integer count;
    private String singerName;

    public Album() {
    }

    public String getAlbumDiagonal() {
        return albumDiagonal;
    }

    public void setAlbumDiagonal(String albumDiagonal) {
        this.albumDiagonal = albumDiagonal;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
}
