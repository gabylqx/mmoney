package com.mmoney.service.impl;

import com.mmoney.dao.BtoloanMapper;
import com.mmoney.pojo.Btoloan;
import com.mmoney.service.BtoloanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: mmoney
 * @description: 借贷子表实现层
 * @author: Li.QiXuan
 * @create: 2019-08-30 13:19
 **/

@Service("btoloanService")
public class BtoloanServiceImpl implements BtoloanService {
    @Autowired(required = false)
    private BtoloanMapper btoloanMapper;

    @Override
    public Btoloan selectBtoloanById(Integer  btoId) {
        return btoloanMapper.selectBtoloanById(btoId);
    }


    @Override
    public boolean updateBtoloan(Btoloan btoloan) {
        if (btoloanMapper.UpdateBtoloan(btoloan)>0) {
            System.out.println("true");
            return true;
        }
        return false;
    }

    @Override
    public int insertBtoloan(Btoloan btoloan) {
        return btoloanMapper.insertBtoloan(btoloan);
    }

    @Override
    public List<Btoloan> selectBtoloanListById(String btoTolId) {
        return btoloanMapper.selectBtoloanListById(btoTolId);
    }
}
