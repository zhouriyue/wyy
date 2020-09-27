package com.gxuwz.beethoven.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.gxuwz.beethoven.model.vo.Href;

import java.io.Serializable;

public class SongListsMusic implements Serializable, Parcelable {

    private Integer id;
    private String songListId;
    private String reallyId;
    private Links _links;
    private String musicName;
    private String singerName;
    private Integer songTime;

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

    public String getReallyId() {
        return reallyId;
    }

    public void setReallyId(String reallyId) {
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
}
