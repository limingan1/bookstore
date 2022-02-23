package com.atguigu.dao.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	/**
	 * 
	 */
	@Override
	public User getUserByUserNameAndPassWord(User user) {
		// TODO Auto-generated method stub
		String sql = "select * from bs_user where username=? and password=?";
		User bean = this.getBean(sql, user.getUsername(), user.getPassword());
		//返回一个user类
		return bean;
	}

	@Override
	public boolean registUser(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into bs_user(username,password,email) values(?,?,?)";
		// update >0,成功插入一行,影响的行数为1
		//用update来插入
		int update = this.update(sql, user.getUsername(), user.getPassword(),
				user.getEmail());
		//若update>0,则为true
		return update > 0;
	}

	@Override
	public User getUserByUserName(User user) {
		// TODO Auto-generated method stub
		String sql="select * from bs_user where username=?";
		//返回一个user类
		return getBean(sql, user.getUsername());
	}

}
