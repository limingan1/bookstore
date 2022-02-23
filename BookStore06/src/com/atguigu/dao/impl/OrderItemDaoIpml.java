package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderItemDao;

public class OrderItemDaoIpml extends BaseDao<OrderItem> implements
		OrderItemDao {

	@Override
	//根据订单号获取所有购物项
	//方法括号里得有一个带查询的订单号
	public List<OrderItem> getOrderItems(String orderId) {
		// TODO Auto-generated method stub
		String sql="select title,price,total_price totalPrice,order_id orderId,count from bs_order_item where order_id=?";
		List<OrderItem> list = getBeanList(sql,orderId);
		return list;
	}

	@Override
	//该方法为int型
	//保存购物项
	public int saveOrderItem(OrderItem item) {
		// TODO Auto-generated method stub
		String sql="insert into bs_order_item(title,count,price,total_price,order_id) "
				+ "values(?,?,?,?,?)";
		//返回影响的行数
		return update(sql,item.getTitle(),item.getCount(),item.getPrice(),item.getTotalPrice(),item.getOrderId());
	}
	@Override
	//一次性保存订单项
	//相当于是添加新的订单项
	public int saveBatch(List<OrderItem> params){
		String sql="insert into bs_order_item(title,count,price,total_price,order_id) "
				+ "values(?,?,?,?,?)";
		Object[][] obj=new Object[params.size()][5];
		int count=0;
		for (OrderItem item : params) {
			obj[count++]=new Object[]{item.getTitle(),
					item.getCount(),item.getPrice(),item.getTotalPrice()
					,item.getOrderId()};
		}
		batch(sql, obj);
		return 1;
	}

}
