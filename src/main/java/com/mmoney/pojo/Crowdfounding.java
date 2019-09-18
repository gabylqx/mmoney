package com.mmoney.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/*
 * cfId 众筹项目Id
 * cfName 项目名
 * cfUsrId 发起人ID
 * cfTarget 目标金额
 * cfStartDate 开始日期
 * cfDeline 截止日期
 * cfMoney 目前已收到金额
 * cfExplain项目内容
 * cfImgpath 图片地址
 */

public class Crowdfounding {
	private Integer cfId;
	private String cfName;
	private Integer cfUsrId;
	private BigDecimal cfTarget;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cfStartDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cfDeline;
	private BigDecimal cfMoney;
	private String cfExplain;
	private String cfImgpath;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date cfSub;

	public Date getCfSub() {
		return cfSub;
	}

	public void setCfSub(Date cfSub) {
		this.cfSub = cfSub;
	}

	public Crowdfounding() {
	}

	@Override
	public String toString() {
		return "Crowdfounding{" +
				"cfId=" + cfId +
				", cfName='" + cfName + '\'' +
				", cfUsrId=" + cfUsrId +
				", cfTarget=" + cfTarget +
				", cfStartDate=" + cfStartDate +
				", cfDeline=" + cfDeline +
				", cfMoney=" + cfMoney +
				", cfExplain='" + cfExplain + '\'' +
				", cfImgpath='" + cfImgpath + '\'' +
				'}';
	}

	public Integer getCfId() {
		return cfId;
	}

	public void setCfId(Integer cfId) {
		this.cfId = cfId;
	}

	public String getCfName() {
		return cfName;
	}

	public void setCfName(String cfName) {
		this.cfName = cfName;
	}

	public Integer getCfUsrId() {
		return cfUsrId;
	}

	public void setCfUsrId(Integer cfUsrId) {
		this.cfUsrId = cfUsrId;
	}

	public BigDecimal getCfTarget() {
		return cfTarget;
	}

	public void setCfTarget(BigDecimal cfTarget) {
		this.cfTarget = cfTarget;
	}

	public Date getCfStartDate() {
		return cfStartDate;
	}

	public void setCfStartDate(Date cfStartDate) {
		this.cfStartDate = cfStartDate;
	}

	public Date getCfDeline() {
		return cfDeline;
	}

	public void setCfDeline(Date cfDeline) {
		this.cfDeline = cfDeline;
	}

	public BigDecimal getCfMoney() {
		return cfMoney;
	}

	public void setCfMoney(BigDecimal cfMoney) {
		this.cfMoney = cfMoney;
	}

	public String getCfExplain() {
		return cfExplain;
	}

	public void setCfExplain(String cfExplain) {
		this.cfExplain = cfExplain;
	}

	public String getCfImgpath() {
		return cfImgpath;
	}

	public void setCfImgpath(String cfImgpath) {
		this.cfImgpath = cfImgpath;
	}
}
