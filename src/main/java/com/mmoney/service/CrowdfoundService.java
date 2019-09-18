package com.mmoney.service;

import com.mmoney.pojo.Crowdfounding;
import com.mmoney.pojo.Record;

import java.util.List;


public interface CrowdfoundService {
    //插入众筹项目信息
    int insertCrowfound(Crowdfounding crowdfounding);

    //查询所有众筹项目
    List<Crowdfounding> showCrowdfounds();

    //查询单个项目
    Crowdfounding findCrowdById(Integer cfId);

    //查询众筹子表
    Record findRecord(Integer cfId, Integer usrId);

    //插入众筹子表
    Integer insertRecord(Record record);

    //更新众筹金额
    void updateCfMoney(Crowdfounding crowd);

    //查询所有众筹子表
    List<Record> findCrowUsers(Integer cfId);
}
