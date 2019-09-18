package com.mmoney.service.impl;

import com.mmoney.dao.CrowdfoundMapper;
import com.mmoney.pojo.Crowdfounding;
import com.mmoney.pojo.Record;
import com.mmoney.service.CrowdfoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("crowdfoundService")
public class CrowdfoundServiceImpl implements CrowdfoundService {

    @Autowired(required = false)
    private CrowdfoundMapper crowdfoundMapper;

    //插入众筹项目信息
    public int insertCrowfound(Crowdfounding crowdfounding) {
        return crowdfoundMapper.insertCrowfound(crowdfounding);
    }

    //查询所有众筹项目
    public List<Crowdfounding> showCrowdfounds() {
        return crowdfoundMapper.showCrowdfounds();
    }

    //查询单个项目
    public Crowdfounding findCrowdById(Integer cfId) {
        return crowdfoundMapper.findCrowdById(cfId);
    }

    //查询众筹子表
    public Record findRecord(Integer cfId, Integer usrId) {
        return crowdfoundMapper.findRecord(cfId,usrId);
    }

    //插入众筹子表
    public Integer insertRecord(Record record) {
        return crowdfoundMapper.insertRecord(record);
    }

    //更新众筹金额
    public void updateCfMoney(Crowdfounding crowd) {
        crowdfoundMapper.updateCfMoney(crowd);
    }

    //查询所有众筹子表
    public List<Record> findCrowUsers(Integer cfId) {
        return crowdfoundMapper.findCrowUsers(cfId);
    }
}
