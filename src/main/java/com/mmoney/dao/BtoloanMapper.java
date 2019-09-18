package com.mmoney.dao;

import com.mmoney.pojo.Btoloan;

import java.util.List;

/**
 * @program: mmoney
 * @description:
 * @author: Li.QiXuan
 * @create: 2019-08-30 13:20
 **/

public interface BtoloanMapper {
    Btoloan selectBtoloanById(Integer  btoId);
    int UpdateBtoloan(Btoloan btoloan);

    int insertBtoloan(Btoloan btoloan);

    List<Btoloan> selectBtoloanListById(String btoTolId);
}
