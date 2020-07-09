package com.gxuwz.beethoven.model.entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.gxuwz.beethoven.model.vo.Href;

import java.io.Serializable;

public class SongList implements Serializable, Parcelable {

    private String songListId;
    private Integer songNumber;
    private String createDate;
    private Integer shareNumber;
    private String tag;
    private String summary;
    private Integer playNumber;
    private Integer commentNumber;
    private String reallyId;
    private String songListName;
    private String songListUrl;
    private String commends;
    private String songListMusic;
    private Links _links;

    protected SongList(Parcel in) {
        songListId = in.readString();
        if (in.readByte() == 0) {
            songNumber = null;
        } else {
            songNumber = in.readInt();
        }
        createDate = in.readString();
        if (in.readByte() == 0) {
            shareNumber = null;
        } else {
            shareNumber = in.readInt();
        }
        tag = in.readString();
        summary = in.readString();
        if (in.readByte() == 0) {
            playNumber = null;
        } else {
            playNumber = in.readInt();
        }
        if (in.readByte() == 0) {
            commentNumber = null;
        } else {
            commentNumber = in.readInt();
        }
        reallyId = in.readString();
        songListName = in.readString();
        songListUrl = in.readString();
        commends = in.readString();
        songListMusic = in.readString();
    }

    public static final Creator<SongList> CREATOR = new Creator<SongList>() {
        @Override
        public SongList createFromParcel(Parcel in) {
            return new SongList(in);
        }

        @Override
        public SongList[] newArray(int size) {
            return new SongList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(songListId);
        if (songNumber == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(songNumber);
        }
        parcel.writeString(createDate);
        if (shareNumber == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(shareNumber);
        }
        parcel.writeString(tag);
        parcel.writeString(summary);
        if (playNumber == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(playNumber);
        }
        if (commentNumber == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(commentNumber);
        }
        parcel.writeString(reallyId);
        parcel.writeString(songListName);
        parcel.writeString(songListUrl);
        parcel.writeString(commends);
        parcel.writeString(songListMusic);
    }


    public class Links{
        Href songListMusics;
        Href commends;

        public Href getSongListMusics() {
            return songListMusics;
        }

        public void setSongListMusics(Href songListMusics) {
            this.songListMusics = songListMusics;
        }

        public Href getCommends() {
            return commends;
        }

        public void setCommends(Href commends) {
            this.commends = commends;
        }

        @Override
        public String toString() {
            return "Links{" +
                    "songListMusics=" + songListMusics +
                    ", commends=" + commends +
                    '}';
        }
    }


    public String getSongListId() {
        return songListId;
    }

    public void setSongListId(String songListId) {
        this.songListId = songListId;
    }

    public Integer getSongNumber() {
        return songNumber;
    }

    public void setSongNumber(Integer songNumber) {
        this.songNumber = songNumber;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(Integer shareNumber) {
        this.shareNumber = shareNumber;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getReallyId() {
        return reallyId;
    }

    public void setReallyId(String reallyId) {
        this.reallyId = reallyId;
    }

    public String getSongListName() {
        return songListName;
    }

    public void setSongListName(String songListName) {
        this.songListName = songListName;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    public String getSongListUrl() {
        return songListUrl;
    }

    public void setSongListUrl(String songListUrl) {
        this.songListUrl = songListUrl;
    }

    public String getCommends() {
        return commends;
    }

    public void setCommends(String commends) {
        this.commends = commends;
    }

    public String getSongListMusic() {
        return songListMusic;
    }

    public void setSongListMusic(String songListMusic) {
        this.songListMusic = songListMusic;
    }

    @Override
    public String toString() {
        return "SongList{" +
                "songListId='" + songListId + '\'' +
                ", songNumber=" + songNumber +
                ", createDate='" + createDate + '\'' +
                ", shareNumber=" + shareNumber +
                ", tag='" + tag + '\'' +
                ", summary='" + summary + '\'' +
                ", playNumber=" + playNumber +
                ", commentNumber=" + commentNumber +
                ", reallyId='" + reallyId + '\'' +
                ", songListName='" + songListName + '\'' +
                ", songListUrl='" + songListUrl + '\'' +
                ", commends='" + commends + '\'' +
                ", songListMusic='" + songListMusic + '\'' +
                ", _links=" + _links +
                '}';
    }
}
