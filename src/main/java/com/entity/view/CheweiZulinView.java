package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.CheweiZulinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 车位租赁订单
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("chewei_zulin")
public class CheweiZulinView extends CheweiZulinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单状态的值
	*/
	@ColumnInfo(comment="订单状态的字典表值",type="varchar(200)")
	private String cheweiZulinValue;

	//级联表 车位
					 
		/**
		* 车位 的 用户
		*/
		@ColumnInfo(comment="用户",type="int(11)")
		private Integer cheweiYonghuId;
		/**
		* 车位名称
		*/

		@ColumnInfo(comment="车位名称",type="varchar(200)")
		private String cheweiName;
		/**
		* 车位编号
		*/

		@ColumnInfo(comment="车位编号",type="varchar(200)")
		private String cheweiUuidNumber;
		/**
		* 车位照片
		*/

		@ColumnInfo(comment="车位照片",type="varchar(200)")
		private String cheweiPhoto;
		/**
		* 车位位置
		*/

		@ColumnInfo(comment="车位位置",type="varchar(200)")
		private String cheweiAddress;
		/**
		* 车位类型
		*/
		@ColumnInfo(comment="车位类型",type="int(11)")
		private Integer cheweiTypes;
			/**
			* 车位类型的值
			*/
			@ColumnInfo(comment="车位类型的字典表值",type="varchar(200)")
			private String cheweiValue;
		/**
		* 租赁价格/月
		*/
		@ColumnInfo(comment="租赁价格/月",type="decimal(10,2)")
		private Double cheweiNewMoney;
		/**
		* 车位状态
		*/
		@ColumnInfo(comment="车位状态",type="int(11)")
		private Integer cheweiZhuangtaiTypes;
			/**
			* 车位状态的值
			*/
			@ColumnInfo(comment="车位状态的字典表值",type="varchar(200)")
			private String cheweiZhuangtaiValue;
		/**
		* 车位介绍
		*/

		@ColumnInfo(comment="车位介绍",type="text")
		private String cheweiContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer cheweiDelete;
		/**
		* 是否上架
		*/
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			/**
			* 是否上架的值
			*/
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;



	public CheweiZulinView() {

	}

	public CheweiZulinView(CheweiZulinEntity cheweiZulinEntity) {
		try {
			BeanUtils.copyProperties(this, cheweiZulinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单状态的值
	*/
	public String getCheweiZulinValue() {
		return cheweiZulinValue;
	}
	/**
	* 设置： 订单状态的值
	*/
	public void setCheweiZulinValue(String cheweiZulinValue) {
		this.cheweiZulinValue = cheweiZulinValue;
	}


	//级联表的get和set 车位
		/**
		* 获取：车位 的 用户
		*/
		public Integer getCheweiYonghuId() {
			return cheweiYonghuId;
		}
		/**
		* 设置：车位 的 用户
		*/
		public void setCheweiYonghuId(Integer cheweiYonghuId) {
			this.cheweiYonghuId = cheweiYonghuId;
		}

		/**
		* 获取： 车位名称
		*/
		public String getCheweiName() {
			return cheweiName;
		}
		/**
		* 设置： 车位名称
		*/
		public void setCheweiName(String cheweiName) {
			this.cheweiName = cheweiName;
		}

		/**
		* 获取： 车位编号
		*/
		public String getCheweiUuidNumber() {
			return cheweiUuidNumber;
		}
		/**
		* 设置： 车位编号
		*/
		public void setCheweiUuidNumber(String cheweiUuidNumber) {
			this.cheweiUuidNumber = cheweiUuidNumber;
		}

		/**
		* 获取： 车位照片
		*/
		public String getCheweiPhoto() {
			return cheweiPhoto;
		}
		/**
		* 设置： 车位照片
		*/
		public void setCheweiPhoto(String cheweiPhoto) {
			this.cheweiPhoto = cheweiPhoto;
		}

		/**
		* 获取： 车位位置
		*/
		public String getCheweiAddress() {
			return cheweiAddress;
		}
		/**
		* 设置： 车位位置
		*/
		public void setCheweiAddress(String cheweiAddress) {
			this.cheweiAddress = cheweiAddress;
		}
		/**
		* 获取： 车位类型
		*/
		public Integer getCheweiTypes() {
			return cheweiTypes;
		}
		/**
		* 设置： 车位类型
		*/
		public void setCheweiTypes(Integer cheweiTypes) {
			this.cheweiTypes = cheweiTypes;
		}


			/**
			* 获取： 车位类型的值
			*/
			public String getCheweiValue() {
				return cheweiValue;
			}
			/**
			* 设置： 车位类型的值
			*/
			public void setCheweiValue(String cheweiValue) {
				this.cheweiValue = cheweiValue;
			}

		/**
		* 获取： 租赁价格/月
		*/
		public Double getCheweiNewMoney() {
			return cheweiNewMoney;
		}
		/**
		* 设置： 租赁价格/月
		*/
		public void setCheweiNewMoney(Double cheweiNewMoney) {
			this.cheweiNewMoney = cheweiNewMoney;
		}
		/**
		* 获取： 车位状态
		*/
		public Integer getCheweiZhuangtaiTypes() {
			return cheweiZhuangtaiTypes;
		}
		/**
		* 设置： 车位状态
		*/
		public void setCheweiZhuangtaiTypes(Integer cheweiZhuangtaiTypes) {
			this.cheweiZhuangtaiTypes = cheweiZhuangtaiTypes;
		}


			/**
			* 获取： 车位状态的值
			*/
			public String getCheweiZhuangtaiValue() {
				return cheweiZhuangtaiValue;
			}
			/**
			* 设置： 车位状态的值
			*/
			public void setCheweiZhuangtaiValue(String cheweiZhuangtaiValue) {
				this.cheweiZhuangtaiValue = cheweiZhuangtaiValue;
			}

		/**
		* 获取： 车位介绍
		*/
		public String getCheweiContent() {
			return cheweiContent;
		}
		/**
		* 设置： 车位介绍
		*/
		public void setCheweiContent(String cheweiContent) {
			this.cheweiContent = cheweiContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getCheweiDelete() {
			return cheweiDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setCheweiDelete(Integer cheweiDelete) {
			this.cheweiDelete = cheweiDelete;
		}
		/**
		* 获取： 是否上架
		*/
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}
		/**
		* 设置： 是否上架
		*/
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}


	@Override
	public String toString() {
		return "CheweiZulinView{" +
			", cheweiZulinValue=" + cheweiZulinValue +
			", cheweiName=" + cheweiName +
			", cheweiUuidNumber=" + cheweiUuidNumber +
			", cheweiPhoto=" + cheweiPhoto +
			", cheweiAddress=" + cheweiAddress +
			", cheweiNewMoney=" + cheweiNewMoney +
			", cheweiContent=" + cheweiContent +
			", cheweiDelete=" + cheweiDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", newMoney=" + newMoney +
			"} " + super.toString();
	}
}
