package com.gxuwz.beethoven.model.entity.current;

import java.util.Date;

public class Song {

    /** 歌曲id */
    private Long songId;

    /** 歌曲名 */
    private String songName;

    /** 歌单封面 */
    private String coverPicture;

    /** 时长 */
    private Long duration;

    /** 发行时间 */
    private Date issuingDate;

    /** mv */
    private String mvUrl;

    /** 是否收费 */
    private Integer isCharge;

    /** 是否有版权 */
    private Integer isCopyright;

    /** 是否是单曲 */
    private Integer isSingle;

    /** 标注音质(S) */
    private String standardUrl;

    /** 高品质（HQ） */
    private String hqUrl;

    /** 超音质（SQ） */
    private String sqUrl;

    /** 无损（WP） */
    private String witPreUrl;

    /** 歌词id */
    private Long lyrId;

    /** 删除时间 */
    private Date delTime;

    /** 删除标识符 */
    private Integer delFlag;

    public Song() {
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

    public String getMvUrl() {
        return mvUrl;
    }

    public void setMvUrl(String mvUrl) {
        this.mvUrl = mvUrl;
    }

    public Integer getIsCharge() {
        return isCharge;
    }

    public void setIsCharge(Integer isCharge) {
        this.isCharge = isCharge;
    }

    public Integer getIsCopyright() {
        return isCopyright;
    }

    public void setIsCopyright(Integer isCopyright) {
        this.isCopyright = isCopyright;
    }

    public Integer getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Integer isSingle) {
        this.isSingle = isSingle;
    }

    public String getStandardUrl() {
        return standardUrl;
    }

    public void setStandardUrl(String standardUrl) {
        this.standardUrl = standardUrl;
    }

    public String getHqUrl() {
        return hqUrl;
    }

    public void setHqUrl(String hqUrl) {
        this.hqUrl = hqUrl;
    }

    public String getSqUrl() {
        return sqUrl;
    }

    public void setSqUrl(String sqUrl) {
        this.sqUrl = sqUrl;
    }

    public String getWitPreUrl() {
        return witPreUrl;
    }

    public void setWitPreUrl(String witPreUrl) {
        this.witPreUrl = witPreUrl;
    }

    public Long getLyrId() {
        return lyrId;
    }

    public void setLyrId(Long lyrId) {
        this.lyrId = lyrId;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }
}
