package com.mmoney.service;

import com.mmoney.pojo.Btoloan;
import com.mmoney.pojo.PageChoice;
import com.mmoney.pojo.Toloan;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

public interface AdminService {
    /*管理员功能*/
    //利息总和查询
    BigDecimal queryIntes();
    BigDecimal queryInte();

    //贷款信息列表
    List<Toloan> queryToloanDetil();
    List<Toloan> queryToloanDetilPart(PageChoice pageChoice);

    //获取贷款笔数
    List<Btoloan> queryBtolanById(String btoTolId);
}
