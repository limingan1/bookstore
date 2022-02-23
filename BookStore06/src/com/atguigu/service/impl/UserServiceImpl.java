package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao ud = new UserDaoImpl();
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return ud.getUserByUserNameAndPassWord(user);
	}

	@Override
	public boolean regist(User user) {
		return ud.registUser(user);
	}

	@Override
	public boolean checkUser(User user) {
		// TODO Auto-generated method stub
		User byUserName = ud.getUserByUserName(user);
		//如果查不到该用户名，则为真
		return byUserName==null;
	}

}
