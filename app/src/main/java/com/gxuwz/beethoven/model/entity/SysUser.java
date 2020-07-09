package com.gxuwz.beethoven.model.entity;


import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.gxuwz.beethoven.model.vo.Href;

import java.io.Serializable;

public class SysUser implements Serializable, Parcelable {

    private String userId;
    private String userName;
    private String userPwd;
    private String nickName;
    private String recommend;
    private String sex;
    private String birthday;
    private Integer area;
    private Integer isMember;
    private Integer grade;
    private Integer menberId;
    private String perPic;
    private String reallyId;
    private Href songLists;

    public SysUser() {
    }

    protected SysUser(Parcel in) {
        userId = in.readString();
        userName = in.readString();
        userPwd = in.readString();
        nickName = in.readString();
        recommend = in.readString();
        sex = in.readString();
        birthday = in.readString();
        if (in.readByte() == 0) {
            area = null;
        } else {
            area = in.readInt();
        }
        if (in.readByte() == 0) {
            isMember = null;
        } else {
            isMember = in.readInt();
        }
        if (in.readByte() == 0) {
            grade = null;
        } else {
            grade = in.readInt();
        }
        if (in.readByte() == 0) {
            menberId = null;
        } else {
            menberId = in.readInt();
        }
        perPic = in.readString();
        reallyId = in.readString();
    }

    public static final Creator<SysUser> CREATOR = new Creator<SysUser>() {
        @Override
        public SysUser createFromParcel(Parcel in) {
            return new SysUser(in);
        }

        @Override
        public SysUser[] newArray(int size) {
            return new SysUser[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getIsMember() {
        return isMember;
    }

    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getMenberId() {
        return menberId;
    }

    public void setMenberId(Integer menberId) {
        this.menberId = menberId;
    }

    public String getPerPic() {
        return perPic;
    }

    public void setPerPic(String perPic) {
        this.perPic = perPic;
    }

    public String getReallyId() {
        return reallyId;
    }

    public void setReallyId(String reallyId) {
        this.reallyId = reallyId;
    }

    public Href getSongLists() {
        return songLists;
    }

    public void setSongLists(Href songLists) {
        this.songLists = songLists;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", recommend='" + recommend + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", area=" + area +
                ", isMember=" + isMember +
                ", grade=" + grade +
                ", menberId=" + menberId +
                ", perPic='" + perPic + '\'' +
                ", reallyId='" + reallyId + '\'' +
                ", songLists=" + songLists +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeString(userName);
        parcel.writeString(userPwd);
        parcel.writeString(nickName);
        parcel.writeString(recommend);
        parcel.writeString(sex);
        parcel.writeString(birthday);
        if (area == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(area);
        }
        if (isMember == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isMember);
        }
        if (grade == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(grade);
        }
        if (menberId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(menberId);
        }
        parcel.writeString(perPic);
        parcel.writeString(reallyId);
    }
}
