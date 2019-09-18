package com.mmoney.dao;

import com.mmoney.pojo.Btoloan;
import com.mmoney.pojo.PageChoice;
import com.mmoney.pojo.Toloan;

import java.math.BigDecimal;
import java.util.List;
/**
 * @program: admin
 * @description:
 * @author: Li.QiXuan
 * @create: 2019-08-30 13:20
 **/

public interface AdminMapper {
    /*管理员功能*/
    //利息总和,已收到查询
    BigDecimal queryIntes();
    BigDecimal queryInte();
    //贷款信息查询
    List<Toloan> queryToloanDetil();

    List<Toloan> queryToloanDetilPart(PageChoice pageChoice);
    //贷款笔数查询
    List<Btoloan> queryBtolanById(String btoTolId);
}
