package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OrderItem;

public interface OrderItemService {
	public void saveItem(List<OrderItem> orderitem);
	public List<OrderItem> getOrderItems(String orderId);
}
