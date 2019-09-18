package com.mmoney.pojo;

import java.math.BigDecimal;
import java.util.Date;

//借贷信息子表
public class Btoloan {
	private int btoId;//单笔项id
	private String btoTolId;//订单ID
	private BigDecimal btoPrin;//本金
	private BigDecimal btoIntes;//利息
	private Date btoFdate;//最后还款时间
	private int btoStill;//还清状态
	private String btoPeriod;//当前期数

	@Override
	public String toString() {
		return "Btoloan{" +
				"btoId=" + btoId +
				", btoTolId='" + btoTolId + '\'' +
				", btoPrin=" + btoPrin +
				", btoIntes=" + btoIntes +
				", btoFdate=" + btoFdate +
				", btoStill=" + btoStill +
				", btoPeriod='" + btoPeriod + '\'' +
				'}';
	}

	public String getBtoPeriod() {
		return btoPeriod;
	}

	public void setBtoPeriod(String btoPeriod) {
		this.btoPeriod = btoPeriod;
	}

	public int getBtoId() {
		return btoId;
	}

	public void setBtoId(int btoId) {
		this.btoId = btoId;
	}

	public String getBtoTolId() {
		return btoTolId;
	}

	public void setBtoTolId(String btoTolId) {
		this.btoTolId = btoTolId;
	}

	public BigDecimal getBtoPrin() {
		return btoPrin;
	}

	public void setBtoPrin(BigDecimal btoPrin) {
		this.btoPrin = btoPrin;
	}

	public BigDecimal getBtoIntes() {
		return btoIntes;
	}

	public void setBtoIntes(BigDecimal btoIntes) {
		this.btoIntes = btoIntes;
	}

	public Date getBtoFdate() {
		return btoFdate;
	}

	public void setBtoFdate(Date btoFdate) {
		this.btoFdate = btoFdate;
	}

	public int getBtoStill() {
		return btoStill;
	}

	public void setBtoStill(int btoStill) {
		this.btoStill = btoStill;
	}

	public Btoloan() {

	}
}
