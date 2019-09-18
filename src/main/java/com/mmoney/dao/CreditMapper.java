package com.mmoney.dao;

import com.mmoney.pojo.Credit;

/**
 * @program: mmoney
 * @description:
 * @author: Li.QiXuan
 * @create: 2019-08-30 13:20
 **/

public interface CreditMapper {

    Credit selectCreditById(int cdtId);
}
