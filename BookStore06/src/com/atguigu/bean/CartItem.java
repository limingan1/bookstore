package com.atguigu.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable{
	//定义一个Book类，调用其方法
	//要用book的一些属性
	//图书是购物项的一部分
	private Book book;
	//一开始是设置为1的
	private int count;
	//总价与图书类的单价和数量有关
	private double totalPrice;
	//获得某本书
	//该方法为Book类，所以返回一个Book类
	//可以用该方法获取Book属性
	public Book getBook() {
		return book;
	}
	//根据图书id设置某一本书
	public void setBook(Book book) {
		this.book = book;
	}
	//买该书的数量从一到很多，是图书类没有的属性
	//与销量和库存有关
	public int getCount() {
		return count;
	}
	//获取当前某一购物项的总价
	//总价是cartItem的属性
	//必须包含这本书，因为要用到它的属性进行操作
	public double getTotalPrice() {
		//用getBook()方法，它为Book类
		//获取这本书的单价
		//单价是book的属性
		BigDecimal price = new BigDecimal(getBook().getPrice()+"");
		//获取购买这本书的数量
		//购买数量是购物项（cartItem）的属性
		BigDecimal count = new BigDecimal(getCount()+"");
		//总价=单价*数量
		BigDecimal multiply = price.multiply(count);
		//返回总价的double值
		return multiply.doubleValue();
	}
	//可以不要，因为是根据图书单价和要购买的数量决定的，不是自己设置的
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Override
	public String toString() {
		return "cartItem [book=" + book + ", count=" + count + ", totalPrice="
				+ totalPrice + "]";
	}
	public CartItem(Book book, int count, double totalPrice) {
		super();
		this.book = book;
		this.count = count;
		this.totalPrice = totalPrice;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	//修改图书购买数量的时候用得上
	public void setCount(int count) {
		this.count = count;
	}
	
}
