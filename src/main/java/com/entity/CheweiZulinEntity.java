package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 车位租赁订单
 *
 * @author 
 * @email
 */
@TableName("chewei_zulin")
public class CheweiZulinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public CheweiZulinEntity() {

	}

	public CheweiZulinEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 订单编号
     */
    @ColumnInfo(comment="订单编号",type="varchar(200)")
    @TableField(value = "chewei_zulin_uuid_number")

    private String cheweiZulinUuidNumber;


    /**
     * 车位
     */
    @ColumnInfo(comment="车位",type="int(11)")
    @TableField(value = "chewei_id")

    private Integer cheweiId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 购买月份
     */
    @ColumnInfo(comment="购买月份",type="int(11)")
    @TableField(value = "buy_number")

    private Integer buyNumber;


    /**
     * 实付价格
     */
    @ColumnInfo(comment="实付价格",type="decimal(10,2)")
    @TableField(value = "chewei_zulin_true_price")

    private Double cheweiZulinTruePrice;


    /**
     * 订单状态
     */
    @ColumnInfo(comment="订单状态",type="int(11)")
    @TableField(value = "chewei_zulin_types")

    private Integer cheweiZulinTypes;


    /**
     * 支付时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="支付时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 车位到期时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="车位到期时间",type="timestamp")
    @TableField(value = "daoqi_time")

    private Date daoqiTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CheweiZulin{" +
            ", id=" + id +
            ", cheweiZulinUuidNumber=" + cheweiZulinUuidNumber +
            ", cheweiId=" + cheweiId +
            ", yonghuId=" + yonghuId +
            ", buyNumber=" + buyNumber +
            ", cheweiZulinTruePrice=" + cheweiZulinTruePrice +
            ", cheweiZulinTypes=" + cheweiZulinTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", daoqiTime=" + DateUtil.convertString(daoqiTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
