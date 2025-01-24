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
 * 车位预约
 *
 * @author 
 * @email
 */
@TableName("cheweiyuyue")
public class CheweiyuyueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public CheweiyuyueEntity() {

	}

	public CheweiyuyueEntity(T t) {
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
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 车位预约编号
     */
    @ColumnInfo(comment="车位预约编号",type="varchar(200)")
    @TableField(value = "cheweiyuyue_uuid_number")

    private String cheweiyuyueUuidNumber;


    /**
     * 车位预约名称
     */
    @ColumnInfo(comment="车位预约名称",type="varchar(200)")
    @TableField(value = "cheweiyuyue_name")

    private String cheweiyuyueName;


    /**
     * 车位类型
     */
    @ColumnInfo(comment="车位类型",type="int(11)")
    @TableField(value = "chewei_types")

    private Integer cheweiTypes;


    /**
     * 租赁月份
     */
    @ColumnInfo(comment="租赁月份",type="int(11)")
    @TableField(value = "cheweiyuyue_yuefen")

    private Integer cheweiyuyueYuefen;


    /**
     * 预约缘由
     */
    @ColumnInfo(comment="预约缘由",type="text")
    @TableField(value = "cheweiyuyue_content")

    private String cheweiyuyueContent;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="预约时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 申请状态
     */
    @ColumnInfo(comment="申请状态",type="int(11)")
    @TableField(value = "cheweiyuyue_yesno_types")

    private Integer cheweiyuyueYesnoTypes;


    /**
     * 审核意见
     */
    @ColumnInfo(comment="审核意见",type="text")
    @TableField(value = "cheweiyuyue_yesno_text")

    private String cheweiyuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "cheweiyuyue_shenhe_time")

    private Date cheweiyuyueShenheTime;


    /**
     * 创建时间   listShow
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
	 * 获取：车位预约编号
	 */
    public String getCheweiyuyueUuidNumber() {
        return cheweiyuyueUuidNumber;
    }
    /**
	 * 设置：车位预约编号
	 */

    public void setCheweiyuyueUuidNumber(String cheweiyuyueUuidNumber) {
        this.cheweiyuyueUuidNumber = cheweiyuyueUuidNumber;
    }
    /**
	 * 获取：车位预约名称
	 */
    public String getCheweiyuyueName() {
        return cheweiyuyueName;
    }
    /**
	 * 设置：车位预约名称
	 */

    public void setCheweiyuyueName(String cheweiyuyueName) {
        this.cheweiyuyueName = cheweiyuyueName;
    }
    /**
	 * 获取：车位类型
	 */
    public Integer getCheweiTypes() {
        return cheweiTypes;
    }
    /**
	 * 设置：车位类型
	 */

    public void setCheweiTypes(Integer cheweiTypes) {
        this.cheweiTypes = cheweiTypes;
    }
    /**
	 * 获取：租赁月份
	 */
    public Integer getCheweiyuyueYuefen() {
        return cheweiyuyueYuefen;
    }
    /**
	 * 设置：租赁月份
	 */

    public void setCheweiyuyueYuefen(Integer cheweiyuyueYuefen) {
        this.cheweiyuyueYuefen = cheweiyuyueYuefen;
    }
    /**
	 * 获取：预约缘由
	 */
    public String getCheweiyuyueContent() {
        return cheweiyuyueContent;
    }
    /**
	 * 设置：预约缘由
	 */

    public void setCheweiyuyueContent(String cheweiyuyueContent) {
        this.cheweiyuyueContent = cheweiyuyueContent;
    }
    /**
	 * 获取：预约时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：预约时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：申请状态
	 */
    public Integer getCheweiyuyueYesnoTypes() {
        return cheweiyuyueYesnoTypes;
    }
    /**
	 * 设置：申请状态
	 */

    public void setCheweiyuyueYesnoTypes(Integer cheweiyuyueYesnoTypes) {
        this.cheweiyuyueYesnoTypes = cheweiyuyueYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getCheweiyuyueYesnoText() {
        return cheweiyuyueYesnoText;
    }
    /**
	 * 设置：审核意见
	 */

    public void setCheweiyuyueYesnoText(String cheweiyuyueYesnoText) {
        this.cheweiyuyueYesnoText = cheweiyuyueYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getCheweiyuyueShenheTime() {
        return cheweiyuyueShenheTime;
    }
    /**
	 * 设置：审核时间
	 */

    public void setCheweiyuyueShenheTime(Date cheweiyuyueShenheTime) {
        this.cheweiyuyueShenheTime = cheweiyuyueShenheTime;
    }
    /**
	 * 获取：创建时间   listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间   listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Cheweiyuyue{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", cheweiyuyueUuidNumber=" + cheweiyuyueUuidNumber +
            ", cheweiyuyueName=" + cheweiyuyueName +
            ", cheweiTypes=" + cheweiTypes +
            ", cheweiyuyueYuefen=" + cheweiyuyueYuefen +
            ", cheweiyuyueContent=" + cheweiyuyueContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", cheweiyuyueYesnoTypes=" + cheweiyuyueYesnoTypes +
            ", cheweiyuyueYesnoText=" + cheweiyuyueYesnoText +
            ", cheweiyuyueShenheTime=" + DateUtil.convertString(cheweiyuyueShenheTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
