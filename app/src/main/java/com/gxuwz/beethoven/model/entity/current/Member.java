package com.gxuwz.beethoven.model.entity.current;

import java.util.Date;

/**
 * 会员信息管理对象 member
 * 
 * @author ruoyi
 * @date 2020-10-09
 */
public class Member implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    /** 会员编号 */
    private Long mId;

    /** 会员编号 */
    private Long validDay;

    /** 等级 */
    private Integer mGrade;

    /** 用户编号 */
    private Long userId;

    /** 注册时间 */
    private Date enrollDate;

    public void setmId(Long mId) 
    {
        this.mId = mId;
    }

    public Long getmId() 
    {
        return mId;
    }

    public Long getValidDay() {
        return validDay;
    }

    public void setValidDay(Long validDay) {
        this.validDay = validDay;
    }

    public void setmGrade(Integer mGrade)
    {
        this.mGrade = mGrade;
    }

    public Integer getmGrade() 
    {
        return mGrade;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "mId=" + mId +
                ", validDay=" + validDay +
                ", mGrade=" + mGrade +
                ", userId=" + userId +
                ", enrollDate=" + enrollDate +
                '}';
    }
}
