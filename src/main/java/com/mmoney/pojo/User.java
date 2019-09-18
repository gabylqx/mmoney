package com.mmoney.pojo;

import java.math.BigDecimal;
import java.util.List;

/*
 * 用户id       userId
 * 电话                                userTel
 * 姓名								   userName
 * 密码                                userPswd
 * 邮箱                                userEmail
 * 利率                                userRate
 * 身份证                            userIDcard
 * 年龄                                userAge
 * 职业                                userJob
 * 借贷信息id    						userLoanId
 * 管理员权限                			 userGrant
 * 收入   					 		userSalary
 * */
public class User {
	private Integer usrId;
	private String usrTel;
	private String usrName;
	private String usrPswd;
	private String usrEmail;
	private BigDecimal usrRate;
	private String usrIdcard;
	private Integer usrAge;
	private String usrJob;
	private List<Toloan> toloans;
	private String usrGrant;
	private String usrSalary;
	private Integer UsrCredit;
	private BigDecimal usrQuota;//总额度
	private BigDecimal usrExquota;//已用额度

	public User() {
	}

	@Override
	public String toString() {
		return "User{" +
				"usrId=" + usrId +
				", usrTel='" + usrTel + '\'' +
				", usrName='" + usrName + '\'' +
				", usrPswd='" + usrPswd + '\'' +
				", usrEmail='" + usrEmail + '\'' +
				", usrRate=" + usrRate +
				", usrIdcard='" + usrIdcard + '\'' +
				", usrAge=" + usrAge +
				", usrJob='" + usrJob + '\'' +
				", toloans=" + toloans +
				", usrGrant='" + usrGrant + '\'' +
				", usrSalary='" + usrSalary + '\'' +
				", UsrCredit=" + UsrCredit +
				", usrQuota=" + usrQuota +
				", usrExquota=" + usrExquota +
				'}';
	}

	public Integer getUsrCredit() {
		return UsrCredit;
	}

	public void setUsrCredit(Integer usrCredit) {
		UsrCredit = usrCredit;
	}

	public String  getUsrSalary() {
		return usrSalary;
	}

	public void setUsrSalary(String usrSalary) {
		this.usrSalary = usrSalary;
	}

	public Integer getUsrId() {
		return usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public String getUsrTel() {
		return usrTel;
	}

	public void setUsrTel(String usrTel) {
		this.usrTel = usrTel;
	}

	public String getUsrName() {
		return usrName;
	}

	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}

	public String getUsrPswd() {
		return usrPswd;
	}

	public void setUsrPswd(String usrPswd) {
		this.usrPswd = usrPswd;
	}

	public String getUsrEmail() {
		return usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	public BigDecimal getUsrRate() {
		return usrRate;
	}

	public void setUsrRate(BigDecimal usrRate) {
		this.usrRate = usrRate;
	}

	public String getUsrIdcard() {
		return usrIdcard;
	}

	public void setUsrIdcard(String usrIdcard) {
		this.usrIdcard = usrIdcard;
	}

	public Integer getUsrAge() {
		return usrAge;
	}

	public void setUsrAge(Integer usrAge) {
		this.usrAge = usrAge;
	}

	public String getUsrJob() {
		return usrJob;
	}

	public void setUsrJob(String usrJob) {
		this.usrJob = usrJob;
	}

	public List<Toloan> getToloans() {
		return toloans;
	}

	public void setToloans(List<Toloan> toloans) {
		this.toloans = toloans;
	}

	public String getUsrGrant() {
		return usrGrant;
	}

	public void setUsrGrant(String usrGrant) {
		this.usrGrant = usrGrant;
	}

	public BigDecimal getUsrQuota() {
		return usrQuota;
	}

	public void setUsrQuota(BigDecimal usrQuota) {
		this.usrQuota = usrQuota;
	}

	public BigDecimal getUsrExquota() {
		return usrExquota;
	}

	public void setUsrExquota(BigDecimal usrExquota) {
		this.usrExquota = usrExquota;
	}

}
