package com.gxuwz.beethoven.model.entity.current;

public class PlayList {
    private Integer id;
    private Long slId;
    private Long songId;
    /** 播放等级 */
    private int playGrade;

    public PlayList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSlId() {
        return slId;
    }

    public void setSlId(Long slId) {
        this.slId = slId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public int getPlayGrade() {
        return playGrade;
    }

    public void setPlayGrade(int playGrade) {
        this.playGrade = playGrade;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", slId=" + slId +
                ", songId=" + songId +
                ", playGrade=" + playGrade +
                '}';
    }
}
