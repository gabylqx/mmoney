package com.mmoney.service.impl;

import com.mmoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmoney.dao.UserMapper;
import com.mmoney.pojo.User;

import java.math.BigDecimal;


@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired(required = false)
	private UserMapper userMapper ;


	@Override
	public User loginCheck(User user) {
		System.out.println("imple的User："+user);
		User userTrue = userMapper.loginCheck(user);
		System.out.println("数据库返回user："+userTrue);
		return userTrue;
	}

    @Override
    public BigDecimal rateUpdate(int creditCode) {
		if (creditCode>=710&&creditCode<=910)
			return BigDecimal.valueOf(0.0005);
		if (creditCode>910)
			return BigDecimal.valueOf(0.0003);
		return null;
    }

	@Override
	public BigDecimal creditUpdate(int creditCode) {
		BigDecimal bd = null;
		if (creditCode>=710&&creditCode<830){
			bd = BigDecimal.valueOf(creditCode-710);
			bd = bd.multiply(BigDecimal.valueOf(162.5));
			bd = bd.add(BigDecimal.valueOf(500));
		}else if (creditCode>=830&&creditCode<1030){
			bd = BigDecimal.valueOf(creditCode-830);
			bd = bd.multiply(BigDecimal.valueOf(25));
			bd = bd.add(BigDecimal.valueOf(500));
		}else if (creditCode>=1030){
			bd = BigDecimal.valueOf(creditCode-1030);
			bd = bd.multiply(BigDecimal.valueOf(225));
			bd = bd.add(BigDecimal.valueOf(500));
		}
		System.out.println("额度："+bd);
		if (bd!=null) {
			if (bd.compareTo(BigDecimal.valueOf(500)) == -1)
				bd = BigDecimal.valueOf(500);
			if (bd.compareTo(BigDecimal.valueOf(50000)) == 1)
				bd = BigDecimal.valueOf(50000);
		}
		Integer lucky = (int)((Math.random()*9+1)*50);
		System.out.println("幸运值："+lucky);
		bd = bd.add(BigDecimal.valueOf(lucky));
		return bd;
	}

	//账号校验
	@Override
	public User queryUserByTel(String usrTel){
		return userMapper.queryUserByTel(usrTel);
	}
	//用户注册
	@Override
	public int userReg(User user){
		return  userMapper.userReg(user);
	}

	//账号更新
	@Override
	public Integer userUpdateByTel(String usrTel){
		return userUpdateByTel(usrTel);
	}

	//账号更新 信息更新
	@Override
	public int updateUserByUser(User user) {
		return 	userMapper.updateUserByUser(user);

	}

	@Override
	public User queryUserByUser(User loginUser) {
		return userMapper.queryUserByUser(loginUser);
	}

	@Override
	public User queryUserByUserId(Integer tolUsrId) {
		return userMapper.queryUserByUserId(tolUsrId);
	}

	@Override
	public Integer updateUserPswdByUser(User user) {
		return userMapper.updateUserPswdByUser(user);
	}
}
