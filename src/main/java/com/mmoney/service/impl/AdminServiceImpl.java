package com.mmoney.service.impl;

import com.mmoney.dao.AdminMapper;
import com.mmoney.dao.UserMapper;
import com.mmoney.pojo.Btoloan;
import com.mmoney.pojo.PageChoice;
import com.mmoney.pojo.Toloan;
import com.mmoney.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
    @Autowired(required = false)
    private AdminMapper adminMapper;

    /*管理员功能*/
    //利息总和查询
    @Override
    public BigDecimal queryIntes(){
        return adminMapper.queryIntes();
    }
    @Override
    public BigDecimal queryInte(){
        return adminMapper.queryInte();
    }
    //贷款信息查询
    @Override
    public List<Toloan> queryToloanDetil() {
        return adminMapper.queryToloanDetil();
    }

    @Override
    public List<Toloan> queryToloanDetilPart(PageChoice pageChoice) {
        return adminMapper.queryToloanDetilPart(pageChoice);
    }
    //贷款笔数查询
    @Override
    public List<Btoloan> queryBtolanById(String btoTolId) {
        return adminMapper.queryBtolanById(btoTolId);
    }
}
