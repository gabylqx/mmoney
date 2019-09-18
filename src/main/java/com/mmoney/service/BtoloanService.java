package com.mmoney.service;

import com.mmoney.pojo.Btoloan;

import java.util.List;

/**
 * @program: mmoney
 * @description: 借贷子表
 * @author: Li.QiXuan
 * @create: 2019-08-30 13:18
 **/

public interface BtoloanService {
    //通过子表id查询
    Btoloan selectBtoloanById(Integer btoId);
    //更新子表订单信息
    boolean updateBtoloan(Btoloan btoloan);

    int insertBtoloan(Btoloan btoloan);

    List<Btoloan> selectBtoloanListById(String btoTolId);
}
