package com.gxuwz.beethoven.model.entity.current;

import java.util.Date;

/**
 * 歌手信息对象 singer
 * 
 * @author ruoyi
 * @date 2020-10-09
 */
public class Singer implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    /** 歌手编号 */
    private Long sinId;

    /** 歌手名 */
    private String sinName;

    /** 歌手昵称 */
    private String nickname;

    /** 性别 */
    private Integer sex;

    /** 年龄 */
    private Integer year;

    /** 歌手类型 */
    private Integer sinType;

    /** 住址 */
    private String area;

    /** 歌曲编号 */
    private Integer songNumber;

    /** 关注数 */
    private Integer followerNumber;

    /** 专辑数 */
    private Integer albumNumber;

    /** mv数 */
    private Integer mvNumber;

    /** 详情 */
    private String detail;

    /** 影响力 */
    private String influencePower;

    /** 认证信息 */
    private String certInfo;

    /** 早期经历 */
    private String earlyExp;

    /** 演绎经历 */
    private String performingExp;

    /** 删除标识符 */
    private Integer delFlag;

    /** 删除时间 */
    private Date delTime;

    public void setSinId(Long sinId) 
    {
        this.sinId = sinId;
    }

    public Long getSinId() 
    {
        return sinId;
    }
    public void setSinName(String sinName) 
    {
        this.sinName = sinName;
    }

    public String getSinName() 
    {
        return sinName;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setSex(Integer sex) 
    {
        this.sex = sex;
    }

    public Integer getSex() 
    {
        return sex;
    }
    public void setYear(Integer year) 
    {
        this.year = year;
    }

    public Integer getYear() 
    {
        return year;
    }
    public void setSinType(Integer sinType) 
    {
        this.sinType = sinType;
    }

    public Integer getSinType() 
    {
        return sinType;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setSongNumber(Integer songNumber) 
    {
        this.songNumber = songNumber;
    }

    public Integer getSongNumber() 
    {
        return songNumber;
    }
    public void setFollowerNumber(Integer followerNumber) 
    {
        this.followerNumber = followerNumber;
    }

    public Integer getFollowerNumber() 
    {
        return followerNumber;
    }
    public void setAlbumNumber(Integer albumNumber) 
    {
        this.albumNumber = albumNumber;
    }

    public Integer getAlbumNumber() 
    {
        return albumNumber;
    }
    public void setMvNumber(Integer mvNumber) 
    {
        this.mvNumber = mvNumber;
    }

    public Integer getMvNumber() 
    {
        return mvNumber;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }
    public void setInfluencePower(String influencePower) 
    {
        this.influencePower = influencePower;
    }

    public String getInfluencePower() 
    {
        return influencePower;
    }
    public void setCertInfo(String certInfo) 
    {
        this.certInfo = certInfo;
    }

    public String getCertInfo() 
    {
        return certInfo;
    }
    public void setEarlyExp(String earlyExp) 
    {
        this.earlyExp = earlyExp;
    }

    public String getEarlyExp() 
    {
        return earlyExp;
    }
    public void setPerformingExp(String performingExp) 
    {
        this.performingExp = performingExp;
    }

    public String getPerformingExp() 
    {
        return performingExp;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }
    public void setDelTime(Date delTime) 
    {
        this.delTime = delTime;
    }

    public Date getDelTime() 
    {
        return delTime;
    }

}
