package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.Order;

public interface OrderDao {
	public int savaOrder(Order order);
	public int updateStatus(Order order);
	public List<Order> getOrderList();
	public List<Order> getOrderById(Integer userId);
}
