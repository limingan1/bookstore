package com.atguigu.test;

import org.junit.Test;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.User;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.service.BookService;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.service.impl.OrderServiceImpl;

public class OrderServiceTest {
	BookService bs=new BookServiceImpl();
	OrderService os=new OrderServiceImpl();
	@Test
	public void test1(){
		Book book=new Book();
		book.setId(16);
		Book one=bs.getOne(book);
		Cart cart=new Cart();
		cart.addBook2Cart(one);
		cart.addBook2Cart(one);
		String orderId=os.checkout(cart, new User(1,"","",""));
		System.out.println(orderId);
	}
}
