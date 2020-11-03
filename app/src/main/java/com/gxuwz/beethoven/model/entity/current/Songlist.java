package com.gxuwz.beethoven.model.entity.current;

import java.io.Serializable;
import java.util.Date;

public class Songlist implements Serializable {
    /** 歌单id */
    private Long slId;

    /** 歌单名 */
    private String slName;

    /** 歌单封面 */
    private String coverPicture;

    /** 标题 */
    private String slTitle;

    /** 播放量 */
    private Integer playNumber;

    /** 歌曲数 */
    private Integer songNumber;

    /** 收藏量 */
    private Integer colNumber;

    /** 评论量 */
    private Integer commentsNumber;

    /** 分享量 */
    private Integer shareNumber;

    /** 创建者 */
    private Long createById;

    /** 详情 */
    private String detail;

    /** 是否是专辑 */
    private Integer isAlbum;

    /** 歌手 */
    private Long sinId;

    /** 是否公开 */
    private Integer isPublic;

    /** 删除时间 */
    private Date delTime;

    /** 删除标识符 */
    private Integer delFlag;

    public Songlist() {
    }

    public Long getSlId() {
        return slId;
    }

    public void setSlId(Long slId) {
        this.slId = slId;
    }

    public String getSlName() {
        return slName;
    }

    public void setSlName(String slName) {
        this.slName = slName;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public String getSlTitle() {
        return slTitle;
    }

    public void setSlTitle(String slTitle) {
        this.slTitle = slTitle;
    }

    public Integer getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(Integer playNumber) {
        this.playNumber = playNumber;
    }

    public Integer getSongNumber() {
        return songNumber;
    }

    public void setSongNumber(Integer songNumber) {
        this.songNumber = songNumber;
    }

    public Integer getColNumber() {
        return colNumber;
    }

    public void setColNumber(Integer colNumber) {
        this.colNumber = colNumber;
    }

    public Integer getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(Integer commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public Integer getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(Integer shareNumber) {
        this.shareNumber = shareNumber;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getIsAlbum() {
        return isAlbum;
    }

    public void setIsAlbum(Integer isAlbum) {
        this.isAlbum = isAlbum;
    }

    public Long getSinId() {
        return sinId;
    }

    public void setSinId(Long sinId) {
        this.sinId = sinId;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
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
}
