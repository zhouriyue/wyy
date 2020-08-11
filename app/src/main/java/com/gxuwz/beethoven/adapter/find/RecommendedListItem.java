package com.gxuwz.beethoven.adapter.find;

public class RecommendedListItem {
    private String diagonal;
    private String songName;
    private String singer;
    private String detail;

    public RecommendedListItem() {
    }

    public String getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "RecommendedListItem{" +
                "diagonal='" + diagonal + '\'' +
                ", songName='" + songName + '\'' +
                ", singer='" + singer + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
