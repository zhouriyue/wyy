package com.gxuwz.beethoven.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.gxuwz.beethoven.model.entity.my.songlist.SLMore;
import com.gxuwz.beethoven.model.vo.Href;

import java.io.Serializable;
import java.util.List;

public class SongListsMusic implements Serializable, Parcelable {

    private Integer id;
    private String songListId;
    private Links _links;
    private String musicId;
    private String singerId;
    private String musicName;
    private String singerName;
    private Integer songTime;
    private String songCachePath;
    private Integer reallyId;

    private List<SLMore> slMoreList;

    public SongListsMusic() {
    }

    public class Links{
        Href music;
        Href singer;

        public Href getMusic() {
            return music;
        }

        public void setMusic(Href music) {
            this.music = music;
        }

        public Href getSinger() {
            return singer;
        }

        public void setSinger(Href singer) {
            this.singer = singer;
        }
    }

    protected SongListsMusic(Parcel in) {
    }

    public static final Creator<SongListsMusic> CREATOR = new Creator<SongListsMusic>() {
        @Override
        public SongListsMusic createFromParcel(Parcel in) {
            return new SongListsMusic(in);
        }

        @Override
        public SongListsMusic[] newArray(int size) {
            return new SongListsMusic[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId;
    }

    public Integer getReallyId() {
        return reallyId;
    }

    public void setReallyId(Integer reallyId) {
        this.reallyId = reallyId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    public Integer getSongTime() {
        return songTime;
    }

    public void setSongTime(Integer songTime) {
        this.songTime = songTime;
    }

    public List<SLMore> getSlMoreList() {
        return slMoreList;
    }

    public void setSlMoreList(List<SLMore> slMoreList) {
        this.slMoreList = slMoreList;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getSingerId() {
        return singerId;
    }

    public void setSingerId(String singerId) {
        this.singerId = singerId;
    }

    public String getSongCachePath() {
        return songCachePath;
    }

    public void setSongCachePath(String songCachePath) {
        this.songCachePath = songCachePath;
    }

    @Override
    public String toString() {
        return "SongListsMusic{" +
                "id=" + id +
                ", songListId='" + songListId + '\'' +
                ", _links=" + _links +
                ", musicId='" + musicId + '\'' +
                ", singerId='" + singerId + '\'' +
                ", musicName='" + musicName + '\'' +
                ", singerName='" + singerName + '\'' +
                ", songTime=" + songTime +
                ", songCachePath='" + songCachePath + '\'' +
                ", reallyId=" + reallyId +
                ", slMoreList=" + slMoreList +
                '}';
    }
}
