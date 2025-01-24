package com.entity.vo;

import com.entity.CheweiyuyueEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 车位预约
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("cheweiyuyue")
public class CheweiyuyueVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 车位预约编号
     */

    @TableField(value = "cheweiyuyue_uuid_number")
    private String cheweiyuyueUuidNumber;


    /**
     * 车位预约名称
     */

    @TableField(value = "cheweiyuyue_name")
    private String cheweiyuyueName;


    /**
     * 车位类型
     */

    @TableField(value = "chewei_types")
    private Integer cheweiTypes;


    /**
     * 租赁月份
     */

    @TableField(value = "cheweiyuyue_yuefen")
    private Integer cheweiyuyueYuefen;


    /**
     * 预约缘由
     */

    @TableField(value = "cheweiyuyue_content")
    private String cheweiyuyueContent;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 申请状态
     */

    @TableField(value = "cheweiyuyue_yesno_types")
    private Integer cheweiyuyueYesnoTypes;


    /**
     * 审核意见
     */

    @TableField(value = "cheweiyuyue_yesno_text")
    private String cheweiyuyueYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "cheweiyuyue_shenhe_time")
    private Date cheweiyuyueShenheTime;


    /**
     * 创建时间  show3 listShow
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
	 * 设置：车位预约编号
	 */
    public String getCheweiyuyueUuidNumber() {
        return cheweiyuyueUuidNumber;
    }


    /**
	 * 获取：车位预约编号
	 */

    public void setCheweiyuyueUuidNumber(String cheweiyuyueUuidNumber) {
        this.cheweiyuyueUuidNumber = cheweiyuyueUuidNumber;
    }
    /**
	 * 设置：车位预约名称
	 */
    public String getCheweiyuyueName() {
        return cheweiyuyueName;
    }


    /**
	 * 获取：车位预约名称
	 */

    public void setCheweiyuyueName(String cheweiyuyueName) {
        this.cheweiyuyueName = cheweiyuyueName;
    }
    /**
	 * 设置：车位类型
	 */
    public Integer getCheweiTypes() {
        return cheweiTypes;
    }


    /**
	 * 获取：车位类型
	 */

    public void setCheweiTypes(Integer cheweiTypes) {
        this.cheweiTypes = cheweiTypes;
    }
    /**
	 * 设置：租赁月份
	 */
    public Integer getCheweiyuyueYuefen() {
        return cheweiyuyueYuefen;
    }


    /**
	 * 获取：租赁月份
	 */

    public void setCheweiyuyueYuefen(Integer cheweiyuyueYuefen) {
        this.cheweiyuyueYuefen = cheweiyuyueYuefen;
    }
    /**
	 * 设置：预约缘由
	 */
    public String getCheweiyuyueContent() {
        return cheweiyuyueContent;
    }


    /**
	 * 获取：预约缘由
	 */

    public void setCheweiyuyueContent(String cheweiyuyueContent) {
        this.cheweiyuyueContent = cheweiyuyueContent;
    }
    /**
	 * 设置：预约时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：预约时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：申请状态
	 */
    public Integer getCheweiyuyueYesnoTypes() {
        return cheweiyuyueYesnoTypes;
    }


    /**
	 * 获取：申请状态
	 */

    public void setCheweiyuyueYesnoTypes(Integer cheweiyuyueYesnoTypes) {
        this.cheweiyuyueYesnoTypes = cheweiyuyueYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getCheweiyuyueYesnoText() {
        return cheweiyuyueYesnoText;
    }


    /**
	 * 获取：审核意见
	 */

    public void setCheweiyuyueYesnoText(String cheweiyuyueYesnoText) {
        this.cheweiyuyueYesnoText = cheweiyuyueYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getCheweiyuyueShenheTime() {
        return cheweiyuyueShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setCheweiyuyueShenheTime(Date cheweiyuyueShenheTime) {
        this.cheweiyuyueShenheTime = cheweiyuyueShenheTime;
    }
    /**
	 * 设置：创建时间  show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
