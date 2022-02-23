package com.atguigu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;
import com.atguigu.bean.User;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.service.BookService;
import com.atguigu.service.OrderItemService;
import com.atguigu.service.OrderService;

public class OrderServiceImpl implements OrderService {
	OrderDao od=new OrderDaoImpl();
	//新建了一个购物项业务的对象
	OrderItemService ois=new OrderItemServiceImpl();
	//新建了一个图书业务的对象
	BookService bs=new BookServiceImpl();
	@Override
	//购物车结算操作 ，通过获取用户和购物车生成唯一的orderId
	//用户id对应购物车，不登录是无法结账的
	public String checkout(Cart cart,User user) {
		// TODO Auto-generated method stub
		/*long id = Thread.currentThread().getId();
		System.out.println("service中的线程号："+id);*/
		//时间戳
		long millis = System.currentTimeMillis();
		//时间戳加用户id构成订单号
		//orderId为String类型,与方法类型一致
		String orderId=millis+""+user.getId();
		//新建一个order对象
		Order order=new Order();
		//设置时间
		order.setCreateDate(new Date());
		//把订单号设置进去
		order.setOrderId(orderId);
		//订单的总金额就是《购物车》的总金额！！！
		//上面已经获取到了这个购物车
		order.setTotalMoney(cart.getTotalMoney());
		//初始订单状态为未发货
		order.setStatus(0);
		//设置订单用户的id
		//该user对象为方法括号里的，必须传入一个带id的user
		order.setUserId(user.getId());
		//获取到购物车的所有购物项
		//也即是封装订单项对象
		List<CartItem> allItems = cart.getAllItems();
		//用List封装所有订单项
		List<OrderItem> orderItems=new ArrayList<>();
		//遍历,封装一个订单项
		//一个订单项对应一个购物项
		for (CartItem cartItem : allItems) {
			//从购物项的数据得到订单项的属性
			//Book是cartItem里面声明的一个类，可以直接用getter-setter方法获取book类的属性
			//这相当于是OrderItem的toString方法
			//this.title=cartItem.getBook().getTitle()
			//this.count=cartItem.getCount()
			//......
			//有了用户id就能获取对应的购物车，购物项和bookid一一对应
			//时间戳+userid确保了订单的唯一
			OrderItem item = new OrderItem(cartItem.getBook().getTitle()
					,cartItem.getCount(),cartItem.getBook().getPrice(),
					cartItem.getTotalPrice(),orderId);
			//把填充好的订单项加入到封装的订单项对象中
			//item是新建的OrderItem对象
			orderItems.add(item);
		}
		//保存订单，相当于是添加订单
		od.savaOrder(order);
		//保存所有订单项，相当于是添加订单项
		ois.saveItem(orderItems);
		// 修改相应库存，改图书，改每一项
		for (CartItem cartItem : allItems) {
			//bookService.update(book)
			//获取详细信息,图书的信息应该从数据库得到
			Book book=cartItem.getBook();
			Book one=bs.getOne(book);
			//修改库存和销量
			int count=cartItem.getCount();
			one.setStock(one.getStock()-count);
			one.setSales(one.getSales()+count);
			//更新信息
			bs.update(one);
		}
		//6、清空购物车
		cart.clear();
		return orderId;
	}

	@Override
	public void updateStatus(String orderId, String status) {
		// TODO Auto-generated method stub
		Order order=new Order();
		//设置订单号
		order.setOrderId(orderId);
		//status转型为int
		int parseInt = Integer.parseInt(status);
		//设置订单状态
		order.setStatus(parseInt);
		//直接调用od对象修改订单状态
		od.updateStatus(order);

	}

	@Override
	public List<Order> getAllOrder() {
		// TODO Auto-generated method stub
		return od.getOrderList();
	}

	@Override
	public List<Order> getMyOrders(Integer userId) {
		// TODO Auto-generated method stub
		return od.getOrderById(userId);
	}

}
