package com.gxuwz.beethoven.model.entity.current;

import java.util.Date;

/**
 * 歌单收藏对象 collectiones
 * 
 * @author ruoyi
 * @date 2020-10-09
 */
public class Collectiones
{
    private static final long serialVersionUID = 1L;

    /** 收藏id */
    private Long colId;

    /** 歌单id */
    private Long slId;

    /** 收藏者 */
    private Long collectBy;

    /** 删除时间 */
    private Date delTime;

    /** 删除标识符 */
    private Integer delFlag;

    private SysUser sysUser;

    private String wordKey;

    private Songlist songlist;

    public void setColId(Long colId) 
    {
        this.colId = colId;
    }

    public Long getColId() 
    {
        return colId;
    }
    public void setSlId(Long slId) 
    {
        this.slId = slId;
    }

    public Long getSlId() 
    {
        return slId;
    }
    public void setCollectBy(Long collectBy) 
    {
        this.collectBy = collectBy;
    }

    public Long getCollectBy() 
    {
        return collectBy;
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

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getWordKey() {
        return wordKey;
    }

    public void setWordKey(String wordKey) {
        this.wordKey = wordKey;
    }

    public Songlist getSonglist() {
        return songlist;
    }

    public void setSonglist(Songlist songlist) {
        this.songlist = songlist;
    }
}
