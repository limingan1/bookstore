package com.atguigu.test;

import java.math.BigDecimal;

import org.junit.Test;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;

public class CartTest {
	Book book1=new Book(1,"xiyouji","wce",0.01,2,1,"");
	Book book2=new Book(2,"shuihuzhuan","sna",0.06,3,1,"");
	Book book3=new Book(3,"sanguoyanyi","lgz",10,2,1,"");
	Book book4=new Book(4,"hongloumeng","cxq",10,4,1,"");
	@Test
	public void test1(){
		Cart cart = new Cart();
		cart.addBook2Cart(book2);
		cart.addBook2Cart(book1);
		System.out.println("当前总计"+cart.getTotalCount()+"本书");
		System.out.println("总价是："+cart.getTotalMoney());
		System.out.println("购物车里的物品项目："+cart.getAllItems());
//		System.out.println("删除后：");
//		cart.deleteItem(book1.getId().toString());
//		System.out.println("当前总计"+cart.getTotalCount()+"本书");
//		System.out.println("总价是："+cart.getTotalMoney());
//		System.out.println("购物车里的物品项目："+cart.getAllItems());
		/*System.out.println("修改后：");
		cart.updateCount(book2.getId().toString(), 2);
		System.out.println("当前总计"+cart.getTotalCount()+"本书");
		System.out.println("总价是："+cart.getTotalMoney());
		System.out.println("购物车里的物品项目："+cart.getAllItems());*/
	}
	@Test
	public void test2(){
		BigDecimal bd = new BigDecimal(1);
		for(int i=1;i<3;i++){
			bd = bd.multiply(new BigDecimal(i));
		}
		System.out.println(bd);
	}
	@Test
	public void test3(){
		double a=0.01;
		double b=0.06;
//		System.out.println(a+b);
		/*BigDecimal bd1 = new BigDecimal(a);
		BigDecimal bd2 = new BigDecimal(b);
		System.out.println(bd1.add(bd2));*/
		BigDecimal bd1 = new BigDecimal(a+"");
		BigDecimal bd2= new BigDecimal(b+"");
		System.out.println(bd1.add(bd2));
	}
}
