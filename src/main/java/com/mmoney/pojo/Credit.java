package com.mmoney.pojo;

/**
 * @program: mmoney
 * @description: 信用表
 *
 * cdt_id        主键
 * cdt_usr_id    用户id
 * cdt_upbefore  信用分更新前
 * cdt_upafter   信用分更新后
 *
 * @author: Li.QiXuan
 * @create: 2019-08-26 16:21
 **/

public class Credit {

    private Integer cdtId;
    private Integer cdtUsrId;
    private Integer cdtUpBefore;
    private Integer cdtUpAfter;

    public Credit() {

    }

    public Integer getCdtId() {
        return cdtId;
    }

    public void setCdtId(Integer cdtId) {
        this.cdtId = cdtId;
    }

    public Integer getCdtUsrId() {
        return cdtUsrId;
    }

    public void setCdtUsrId(Integer cdtUsrId) {
        this.cdtUsrId = cdtUsrId;
    }

    public Integer getCdtUpBefore() {
        return cdtUpBefore;
    }

    public void setCdtUpBefore(Integer cdtUpBefore) {
        this.cdtUpBefore = cdtUpBefore;
    }

    public Integer getCdtUpAfter() {
        return cdtUpAfter;
    }

    public void setCdtUpAfter(Integer cdtUpAfter) {
        this.cdtUpAfter = cdtUpAfter;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "cdtId=" + cdtId +
                ", cdtUsrId=" + cdtUsrId +
                ", cdtUpBefore=" + cdtUpBefore +
                ", cdtUpAfter=" + cdtUpAfter +
                '}';
    }

    public Credit( Integer cdtUsrId, Integer cdtUpBefore, Integer cdtUpAfter) {
        this.cdtUsrId = cdtUsrId;
        this.cdtUpBefore = cdtUpBefore;
        this.cdtUpAfter = cdtUpAfter;
    }
}
