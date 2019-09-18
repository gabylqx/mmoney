package com.mmoney.service.impl;

import com.mmoney.dao.OverdueMapper;
import com.mmoney.pojo.Overdue;
import com.mmoney.service.OverdueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: mmoney
 * @description: 逾期表service实现层
 * @author: Chen.gm
 * @create: 2019-08-26 22:29
 **/
@Service("overdueService")
public class OverdueServiceImpl implements OverdueService{

    @Autowired(required = false)
    private OverdueMapper overdueMapper;

    public Overdue selectOverdueById(int odeId){
        return overdueMapper.selectOverdueById(odeId);
    }
    @Override
    public List<Overdue> overdueQueryByTel(String usrTel) {
        return overdueMapper.overdueQueryByTel(usrTel);
    }

    @Override
    public List<Overdue> odeTotalQuery(String usrTel) {
        return overdueMapper.odeTotalQuery(usrTel);
    }
}
