package com.entity.model;

import com.entity.CheweiZulinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 车位租赁订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class CheweiZulinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单编号
     */
    private String cheweiZulinUuidNumber;


    /**
     * 车位
     */
    private Integer cheweiId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买月份
     */
    private Integer buyNumber;


    /**
     * 实付价格
     */
    private Double cheweiZulinTruePrice;


    /**
     * 订单状态
     */
    private Integer cheweiZulinTypes;


    /**
     * 支付时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 车位到期时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date daoqiTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单编号
	 */
    public String getCheweiZulinUuidNumber() {
        return cheweiZulinUuidNumber;
    }


    /**
	 * 设置：订单编号
	 */
    public void setCheweiZulinUuidNumber(String cheweiZulinUuidNumber) {
        this.cheweiZulinUuidNumber = cheweiZulinUuidNumber;
    }
    /**
	 * 获取：车位
	 */
    public Integer getCheweiId() {
        return cheweiId;
    }


    /**
	 * 设置：车位
	 */
    public void setCheweiId(Integer cheweiId) {
        this.cheweiId = cheweiId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买月份
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：购买月份
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getCheweiZulinTruePrice() {
        return cheweiZulinTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setCheweiZulinTruePrice(Double cheweiZulinTruePrice) {
        this.cheweiZulinTruePrice = cheweiZulinTruePrice;
    }
    /**
	 * 获取：订单状态
	 */
    public Integer getCheweiZulinTypes() {
        return cheweiZulinTypes;
    }


    /**
	 * 设置：订单状态
	 */
    public void setCheweiZulinTypes(Integer cheweiZulinTypes) {
        this.cheweiZulinTypes = cheweiZulinTypes;
    }
    /**
	 * 获取：支付时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：支付时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：车位到期时间
	 */
    public Date getDaoqiTime() {
        return daoqiTime;
    }


    /**
	 * 设置：车位到期时间
	 */
    public void setDaoqiTime(Date daoqiTime) {
        this.daoqiTime = daoqiTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
