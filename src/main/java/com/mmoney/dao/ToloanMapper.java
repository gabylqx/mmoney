package com.mmoney.dao;

import com.mmoney.pojo.PageChoice;
import com.mmoney.pojo.Toloan;

import java.util.List;
/**
 * @program: mmoney
 * @description:
 * @author: Li.QiXuan
 * @create: 2019-08-30 16:20
 **/
public interface ToloanMapper {

    List<Toloan> selectToloans(Integer usrId);

    List<Toloan> queryToloan(PageChoice pc);

    int queryToloanRecordCnt(PageChoice pc);

    Toloan selectToloansById(String tolId);

    int insertToloan(Toloan toloan);

    //查询本条借贷信息
    Toloan queryThisToloan(Integer tolId);

    int updatetoloan(Toloan toloan);
}
