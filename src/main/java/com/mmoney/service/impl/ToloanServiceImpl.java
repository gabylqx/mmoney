package com.mmoney.service.impl;

import com.mmoney.dao.ToloanMapper;
import com.mmoney.pojo.PageChoice;
import com.mmoney.pojo.Toloan;
import com.mmoney.service.ToloanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("toloanService")
public class ToloanServiceImpl implements ToloanService {

    @Autowired(required = false)
    private ToloanMapper toloanMapper;
    @Override
    public List<Toloan> selectToloans(Integer usrId) {
        return toloanMapper.selectToloans(usrId);
    }

    @Override
    public List<Toloan> queryToloan(PageChoice pc) {
        if (pc.getPageNow() == 0)
            pc.setPageNow(1);
        pc.setBeginRow((pc.getPageNow() - 1) * pc.getPageSize());
        int recordCnt = toloanMapper.queryToloanRecordCnt(pc);
        if (recordCnt % pc.getPageSize() == 0)
            pc.setPageCnt(recordCnt / pc.getPageSize());
        else
            pc.setPageCnt(recordCnt / pc.getPageSize() + 1);
        List<Toloan> list = toloanMapper.queryToloan(pc);
        return list;
        //return toloanMapper.queryToloan(pc);
    }

    @Override
    public Toloan selectToloansById(String tolId) {
        return toloanMapper.selectToloansById(tolId);
    }

    @Override
    public int insertToloan(Toloan toloan) {
        return toloanMapper.insertToloan(toloan);
    }

    @Override
    public int updatetoloan(Toloan toloan) {
        return toloanMapper.updatetoloan(toloan);
    }

    @Override
    public Toloan queryThisToloan(Integer tolId) {
        return toloanMapper.queryThisToloan(tolId);
    }

}
