package com.mmoney.service;


import com.mmoney.pojo.Credit;
import com.mmoney.pojo.User;

import java.util.Map;

public interface CreditService {

    //通过信用表id查找这条信用全部信息
    Credit selectCreditById(int cdtId);
    //授予初始信用分
    int usrCreditinit(Map info);
    //更新信用分
    int addcredit(Credit credit);
}
