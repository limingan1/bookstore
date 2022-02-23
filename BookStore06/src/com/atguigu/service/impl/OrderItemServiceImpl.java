package com.atguigu.service.impl;

import java.util.List;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoIpml;
import com.atguigu.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {
	OrderItemDao oid=new OrderItemDaoIpml();
	@Override
	//保存某一订单项
	//并没有返回什么值
	public void saveItem(List<OrderItem> orderitem) {
		// TODO Auto-generated method stub
		/*for (OrderItem orderItem2 : orderitem) {
			oid.saveOrderItem(orderItem2);
		}*/
		/*long id = Thread.currentThread().getId();
		System.out.println("orderItemDao中的线程号："+id);*/
		//直接用oid对象的saveBatch方法
		oid.saveBatch(orderitem);
	}

	@Override
	//根据订单号  获取  购物项
	//一个订单号对应一个购物项，购物项里可能包含多种图书
	//这是查看详情里面的内容
	public List<OrderItem> getOrderItems(String orderId) {
		// TODO Auto-generated method stub
		
		return oid.getOrderItems(orderId);
	}

}
