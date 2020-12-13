package com.gxuwz.beethoven.model.entity;

import java.util.Date;

public class LatePlay {

    private Long id;
    private Long slId;
    private Long songId;
    private Date playDate;

    public LatePlay() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getPlayDate() {
        return playDate;
    }

    public void setPlayDate(Date playDate) {
        this.playDate = playDate;
    }

    @Override
    public String toString() {
        return "LatePlay{" +
                "id=" + id +
                ", slId=" + slId +
                ", songId=" + songId +
                ", playDate=" + playDate +
                '}';
    }
}
