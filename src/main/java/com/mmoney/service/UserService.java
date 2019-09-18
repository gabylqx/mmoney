package com.mmoney.service;

import com.mmoney.pojo.User;

import java.math.BigDecimal;

public interface UserService {

    //登录校验
    User loginCheck(User user);
    //获取初始利率
    BigDecimal rateUpdate(int creditCode);
    //获取初始额度
    BigDecimal creditUpdate(int creditCode);
    //账号校验
    public User queryUserByTel(String usrTel);
    //用户注册
    public int userReg(User user);
    //账号更新
    Integer userUpdateByTel(String usrTel);
    //账号更新 信用信息
    int updateUserByUser(User user);

    User queryUserByUser(User loginUser);
    //根据userId查User
    User queryUserByUserId(Integer tolUsrId);
    //更新密码通过user对象
    Integer updateUserPswdByUser(User user);
}
