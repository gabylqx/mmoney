package com.mmoney.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//借贷信息主表
public class Toloan{
	private String tolId;//订单ID
	private Integer tolUsrId;//用户ID
	private BigDecimal tolBmoney;//借贷金额
	private BigDecimal tolOmoney;//未还本金
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date tolBdate;//借贷时间
	private Integer tolBstages;//分期比数
	private Integer tolStill;//还清状态
	private List<Btoloan> loanList;//借款情况

	public List<Btoloan> getLoanList() {
		return loanList;
	}

	public void setLoanList(List<Btoloan> loanList) {
		this.loanList = loanList;
	}

	public String getTolId() {
		return tolId;
	}

	public void setTolId(String tolId) {
		this.tolId = tolId;
	}

	public Integer getTolUsrId() {
		return tolUsrId;
	}

	public void setTolUsrId(Integer tolUsrId) {
		this.tolUsrId = tolUsrId;
	}

	public BigDecimal getTolBmoney() {
		return tolBmoney;
	}

	public void setTolBmoney(BigDecimal tolBmoney) {
		this.tolBmoney = tolBmoney;
	}

	public BigDecimal getTolOmoney() {
		return tolOmoney;
	}

	public void setTolOmoney(BigDecimal tolOmoney) {
		this.tolOmoney = tolOmoney;
	}

	public Date getTolBdate() {
		return tolBdate;
	}

	public void setTolBdate(Date tolBdate) {
		this.tolBdate = tolBdate;
	}

	public Integer getTolBstages() {
		return tolBstages;
	}

	public void setTolBstages(Integer tolBstages) {
		this.tolBstages = tolBstages;
	}

	public Integer getTolStill() {
		return tolStill;
	}

	public void setTolStill(Integer tolStill) {
		this.tolStill = tolStill;
	}

	public String toString() {
		return "Tol [tolId=" + tolId + ", tolUsrId=" + tolUsrId
				+ ", tolBmoney=" + tolBmoney + ", tolOmoney=" + tolOmoney
				+ ", tolBdate=" + tolBdate + ", tolBstages=" + tolBstages
				+ ", tolStill=" + tolStill + "]";
	}
	public Toloan() {
	}
}