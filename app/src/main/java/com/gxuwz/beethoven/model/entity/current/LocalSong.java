package com.gxuwz.beethoven.model.entity.current;

import java.util.Date;

public class LocalSong {

    private Long songId;
    private String songName;
    private String singerName;
    private String coverPicture;
    private Long duration;
    private Date issuingDate;
    private String songUrl;
    private String lyrUrl;

    public LocalSong() {
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Date getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(Date issuingDate) {
        this.issuingDate = issuingDate;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getLyrUrl() {
        return lyrUrl;
    }

    public void setLyrUrl(String lyrUrl) {
        this.lyrUrl = lyrUrl;
    }
}
