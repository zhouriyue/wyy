package com.gxuwz.beethoven.model.entity;

import java.io.Serializable;

public class PlayList implements Serializable {

    private Integer id;
    private String songName;
    private String singerName;
    private Integer songTime;
    private String networkUri;
    private String localUri;
    private String songListUri;

    public PlayList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSongTime() {
        return songTime;
    }

    public void setSongTime(Integer songTime) {
        this.songTime = songTime;
    }

    public String getNetworkUri() {
        return networkUri;
    }

    public void setNetworkUri(String networkUri) {
        this.networkUri = networkUri;
    }

    public String getLocalUri() {
        return localUri;
    }

    public void setLocalUri(String localUri) {
        this.localUri = localUri;
    }

    public String getSongListUri() {
        return songListUri;
    }

    public void setSongListUri(String songListUri) {
        this.songListUri = songListUri;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", songName='" + songName + '\'' +
                ", singerName='" + singerName + '\'' +
                ", songTime=" + songTime +
                ", networkUri='" + networkUri + '\'' +
                ", localUri='" + localUri + '\'' +
                ", songListUri='" + songListUri + '\'' +
                '}';
    }
}
