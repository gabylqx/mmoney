package com.mmoney.dao;

import com.mmoney.pojo.User;
/**
 * @program: mmoney
 * @description:
 * @author: Li.QiXuan
 * @create: 2019-08-30 16:24
 **/
public interface UserMapper {

	User loginCheck(User user);

	//账号检验
	User queryUserByTel(String usrTel);
	//用户注册
	int userReg(User user);

	//账号更新
	Integer userUpdateByTel(String usrTel);

	//账号更新  信用信息
	int updateUserByUser(User user);

	//更新密码通过user对象
	Integer updateUserPswdByUser(User user);

	User queryUserByUser(User loginUser);

	//查询user根据userId
	User queryUserByUserId(Integer tolUsrId);
}
