package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.bean.Order;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

	@Override
	public int savaOrder(Order order) {
		// TODO Auto-generated method stub
		/*long id = Thread.currentThread().getId();
		System.out.println("orderDao中的线程号："+id);*/
		String sql="insert into bs_order(order_id,create_date,total_money,status,user_id) values(?,?,?,?,?)";
		int update = update(sql,order.getOrderId(),order.getCreateDate(),order.getTotalMoney(),
				order.getStatus(),order.getUserId());
		return update;
	}

	@Override
	//修改订单状态
	public int updateStatus(Order order) {
		// TODO Auto-generated method stub
		String sql="update bs_order set status=? where order_id=?";
		int update = update(sql,order.getStatus(),order.getOrderId());
		return update;
	}

	@Override
	//获取所有用户的订单
	public List<Order> getOrderList() {
		// TODO Auto-generated method stub
		//select语句记得类和数据库表的属性名字一致，
		//也就是给数据库表属性起别名
		String sql="select order_id orderId,create_date createDate,total_money totalMoney,status,user_id  userId from bs_order";
		List<Order> list = getBeanList(sql);
		return list;
	}

	@Override
	//根据用户id获取其订单
	//其订单可能不止一个
	public List<Order> getOrderById(Integer userId) {
		// TODO Auto-generated method stub
		String sql="select order_id orderId,create_date createDate,total_money totalMoney,status,user_id userId from bs_order where user_id=?";
		List<Order> list = getBeanList(sql, userId);
		return list;
	}

}
