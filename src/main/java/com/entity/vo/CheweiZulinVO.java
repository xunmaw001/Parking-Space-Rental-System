package com.entity.vo;

import com.entity.CheweiZulinEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 车位租赁订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("chewei_zulin")
public class CheweiZulinVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "chewei_zulin_uuid_number")
    private String cheweiZulinUuidNumber;


    /**
     * 车位
     */

    @TableField(value = "chewei_id")
    private Integer cheweiId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 购买月份
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 实付价格
     */

    @TableField(value = "chewei_zulin_true_price")
    private Double cheweiZulinTruePrice;


    /**
     * 订单状态
     */

    @TableField(value = "chewei_zulin_types")
    private Integer cheweiZulinTypes;


    /**
     * 支付时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 车位到期时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "daoqi_time")
    private Date daoqiTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单编号
	 */
    public String getCheweiZulinUuidNumber() {
        return cheweiZulinUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setCheweiZulinUuidNumber(String cheweiZulinUuidNumber) {
        this.cheweiZulinUuidNumber = cheweiZulinUuidNumber;
    }
    /**
	 * 设置：车位
	 */
    public Integer getCheweiId() {
        return cheweiId;
    }


    /**
	 * 获取：车位
	 */

    public void setCheweiId(Integer cheweiId) {
        this.cheweiId = cheweiId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：购买月份
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：购买月份
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getCheweiZulinTruePrice() {
        return cheweiZulinTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setCheweiZulinTruePrice(Double cheweiZulinTruePrice) {
        this.cheweiZulinTruePrice = cheweiZulinTruePrice;
    }
    /**
	 * 设置：订单状态
	 */
    public Integer getCheweiZulinTypes() {
        return cheweiZulinTypes;
    }


    /**
	 * 获取：订单状态
	 */

    public void setCheweiZulinTypes(Integer cheweiZulinTypes) {
        this.cheweiZulinTypes = cheweiZulinTypes;
    }
    /**
	 * 设置：支付时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：支付时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：车位到期时间
	 */
    public Date getDaoqiTime() {
        return daoqiTime;
    }


    /**
	 * 获取：车位到期时间
	 */

    public void setDaoqiTime(Date daoqiTime) {
        this.daoqiTime = daoqiTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
