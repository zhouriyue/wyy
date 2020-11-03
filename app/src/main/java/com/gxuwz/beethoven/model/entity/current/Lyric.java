package com.gxuwz.beethoven.model.entity.current;

import java.util.Date;

/**
 * 歌词对象 lyric
 * 
 * @author ruoyi
 * @date 2020-10-09
 */
public class Lyric
{
    private static final long serialVersionUID = 1L;

    /** 歌词id */
    private Long lyrId;

    /** 歌词名 */
    private String lyrName;

    /** 文件地址 */
    private String lyrUrl;

    /** 发行时间 */
    private Date issuingDate;

    public void setLyrId(Long lyrId) 
    {
        this.lyrId = lyrId;
    }

    public Long getLyrId() 
    {
        return lyrId;
    }
    public void setLyrName(String lyrName) 
    {
        this.lyrName = lyrName;
    }

    public String getLyrName() 
    {
        return lyrName;
    }
    public void setLyrUrl(String lyrUrl) 
    {
        this.lyrUrl = lyrUrl;
    }

    public String getLyrUrl() 
    {
        return lyrUrl;
    }
    public void setIssuingDate(Date issuingDate) 
    {
        this.issuingDate = issuingDate;
    }

    public Date getIssuingDate() {
        return issuingDate;
    }
}
