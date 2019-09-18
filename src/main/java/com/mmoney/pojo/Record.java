package com.mmoney.pojo;

import java.math.BigDecimal;
import java.util.Date;

/*
 * rcId 众筹记录Id
 * rcCfId 众筹项目Id
 * rcUsrId 众筹用户Id
 * rcJoinTime 众筹用户参与时间
 * rcMoney 众筹用户参与金额
 */

public class Record {
	private Integer rcId;
	private Integer rcCfId;
	private Integer rcUsrId;
	private Date rcJoinTime;
	private BigDecimal rcMoney;
	public Integer getRcId() {
		return rcId;
	}
	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}
	public Integer getRcCfId() {
		return rcCfId;
	}
	public void setRcCfId(Integer rcCfId) {
		this.rcCfId = rcCfId;
	}
	public Integer getRcUsrId() {
		return rcUsrId;
	}
	public void setRcUsrId(Integer rcUsrId) {
		this.rcUsrId = rcUsrId;
	}
	public Date getRcJoinTime() {
		return rcJoinTime;
	}
	public void setRcJoinTime(Date rcJoinTime) {
		this.rcJoinTime = rcJoinTime;
	}
	public BigDecimal getRcMoney() {
		return rcMoney;
	}
	public void setRcMoney(BigDecimal rcMoney) {
		this.rcMoney = rcMoney;
	}
	public String toString() {
		return "Record [rcId=" + rcId + ", rcCfId=" + rcCfId + ", rcUsrId="
				+ rcUsrId + ", rcJoinTime=" + rcJoinTime + ", rcMoney="
				+ rcMoney + "]";
	}
	public Record(Integer rcId, Integer rcCfId, Integer rcUsrId,
			Date rcJoinTime, BigDecimal rcMoney) {
		super();
		this.rcId = rcId;
		this.rcCfId = rcCfId;
		this.rcUsrId = rcUsrId;
		this.rcJoinTime = rcJoinTime;
		this.rcMoney = rcMoney;
	}
	public Record() {
		super();
	}
	
	
}
