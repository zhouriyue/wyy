package com.gxuwz.beethoven.model.entity.current;

import java.math.BigDecimal;

/**
 * 套餐信息管理对象 set_meal
 * 
 * @author ruoyi
 * @date 2020-11-01
 */
public class SetMeal
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识 */
    private Long smId;

    /** 套餐名 */
    private String smName;

    /** 当前价格 */
    private Double currentPrices;

    /** 原价 */
    private Double costPrice;

    /** 充值月数 */
    private Integer rechargeMonth;

    /** 套餐类型 0:非包月,1:包月，2：包年，3：包季 */
    private Integer smType;

    /** 详情 */
    private String detail;

    public void setSmId(Long smId) 
    {
        this.smId = smId;
    }

    public Long getSmId() 
    {
        return smId;
    }
    public void setSmName(String smName) 
    {
        this.smName = smName;
    }

    public String getSmName() 
    {
        return smName;
    }
    public void setCurrentPrices(Double currentPrices)
    {
        this.currentPrices = currentPrices;
    }

    public Double getCurrentPrices()
    {
        return currentPrices;
    }
    public void setCostPrice(Double costPrice)
    {
        this.costPrice = costPrice;
    }

    public Double getCostPrice()
    {
        return costPrice;
    }
    public void setRechargeMonth(Integer rechargeMonth) 
    {
        this.rechargeMonth = rechargeMonth;
    }

    public Integer getRechargeMonth() 
    {
        return rechargeMonth;
    }
    public void setSmType(Integer smType) 
    {
        this.smType = smType;
    }

    public Integer getSmType() 
    {
        return smType;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }

}
