package com.mmoney.service;



import com.mmoney.pojo.Overdue;

import java.util.List;

public interface OverdueService {

     //通过逾期表id查找这条逾期全部信息
     Overdue selectOverdueById(int odeId);

     //通过用户名查找逾期记录
     List<Overdue> overdueQueryByTel(String usrTel);

     //通过用户名查找逾期用户信息
     List<Overdue> odeTotalQuery(String usrTel);
}
