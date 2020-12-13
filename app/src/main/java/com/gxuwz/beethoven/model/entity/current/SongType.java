package com.gxuwz.beethoven.model.entity.current;

import java.util.Date;
/**
 * 歌曲类型对象 song_type
 * 
 * @author ruoyi
 * @date 2020-10-09
 */
public class SongType implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    /** 类型id */
    private Long stId;

    /** 类型名 */
    private String stName;

    /** 删除时间 */
    private Date delTime;

    /** 删除标识符 */
    private Integer delFlag;

    public void setStId(Long stId) 
    {
        this.stId = stId;
    }

    public Long getStId() 
    {
        return stId;
    }
    public void setStName(String stName) 
    {
        this.stName = stName;
    }

    public String getStName() 
    {
        return stName;
    }
    public void setDelTime(Date delTime) 
    {
        this.delTime = delTime;
    }

    public Date getDelTime() 
    {
        return delTime;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }

}
