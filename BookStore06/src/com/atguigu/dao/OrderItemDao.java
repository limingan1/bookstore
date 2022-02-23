package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.OrderItem;

public interface OrderItemDao {
	public List<OrderItem> getOrderItems(String orderId);
	public int saveOrderItem(OrderItem item);
	public int saveBatch(List<OrderItem> params);
}
