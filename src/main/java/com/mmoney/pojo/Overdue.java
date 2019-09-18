package com.mmoney.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: mmoney
 * @description: 逾期表
 * ode_id    主键
 * ode_usr_id  外键用户id
 * ode_tol_id  外键借贷订单id
 * ode_time    逾期日期
 * ode_money   逾期金额
 * ode_state   还清状态
 * user        用户id对应的用户信息//alter by:jiang.hc
 * odeNum      用户逾期记录数统计//alter by:jiang.hc
 *
 * @author: Li.QiXuan
 * @create: 2019-08-26 16:47
 **/
public class Overdue {

    private Integer odeId;
    private Integer odeUsrId;
    private Integer odeTolId;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date odeTime;
    private BigDecimal odeMoney;
    private Integer odeState;
    private User user;
    private Integer odeNum;

    public Integer getOdeNum() {
        return odeNum;
    }

    public void setOdeNum(Integer odeNum) {
        this.odeNum = odeNum;
    }

    public Overdue() {
    }

    public Overdue(Integer odeId, Integer odeUsrId, Integer odeTolId, Date odeTime, BigDecimal odeMoney, Integer odeState) {
        this.odeId = odeId;
        this.odeUsrId = odeUsrId;
        this.odeTolId = odeTolId;
        this.odeTime = odeTime;
        this.odeMoney = odeMoney;
        this.odeState = odeState;
    }

    public Integer getOdeId() {
        return odeId;
    }

    public void setOdeId(Integer odeId) {
        this.odeId = odeId;
    }

    public Integer getOdeUsrId() {
        return odeUsrId;
    }

    public void setOdeUsrId(Integer odeUsrId) {
        this.odeUsrId = odeUsrId;
    }

    public Integer getOdeTolId() {
        return odeTolId;
    }

    public void setOdeTolId(Integer odeTolId) {
        this.odeTolId = odeTolId;
    }

    public Date getOdeTime() {
        return odeTime;
    }

    public void setOdeTime(Date odeTime) {
        this.odeTime = odeTime;
    }

    public BigDecimal getOdeMoney() {
        return odeMoney;
    }

    public void setOdeMoney(BigDecimal odeMoney) {
        this.odeMoney = odeMoney;
    }

    public Integer getOdeState() {
        return odeState;
    }

    public void setOdeState(Integer odeState) {
        this.odeState = odeState;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Overdue(Integer odeUsrId, Integer odeTolId, Date odeTime, BigDecimal odeMoney, Integer odeState, User user) {
        this.odeUsrId = odeUsrId;
        this.odeTolId = odeTolId;
        this.odeTime = odeTime;
        this.odeMoney = odeMoney;
        this.odeState = odeState;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Overdue{" +
                "odeId=" + odeId +
                ", odeUsrId=" + odeUsrId +
                ", odeTolId=" + odeTolId +
                ", odeTime=" + odeTime +
                ", odeMoney=" + odeMoney +
                ", odeState=" + odeState +
                ", odeNum=" + odeNum +
                '}';
    }
}
