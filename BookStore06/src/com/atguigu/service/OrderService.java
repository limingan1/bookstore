package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Order;
import com.atguigu.bean.User;

public interface OrderService {
	public String checkout(Cart cart,User user);
	public void updateStatus(String orderId, String status);
	public List<Order> getAllOrder();
	public List<Order> getMyOrders(Integer userId);
}
