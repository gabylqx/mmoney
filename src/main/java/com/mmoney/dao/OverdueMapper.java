package com.mmoney.dao;

import com.mmoney.pojo.Overdue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @program: mmoney
 * @description:
 * @author: Li.QiXuan
 * @create: 2019-08-30 17:20
 **/
public interface OverdueMapper {

   Overdue selectOverdueById(int odeId);
   //通过用户名查找逾期记录
   List<Overdue> overdueQueryByTel(@Param("usrTel")String usrTel);
   //通过用户名查找逾期用户信息
   List<Overdue> odeTotalQuery(@Param("usrTel")String usrTel);
}
