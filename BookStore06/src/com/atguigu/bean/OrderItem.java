package com.atguigu.bean;
//每一个订单项
public class OrderItem {
	//书名
	private String title;
	//书的数量
	private int count;
	//单价
	private double price;
	//单个订单项的总价格
	private double totalPrice;
	private String orderId;
	@Override
	public String toString() {
		return "OrderItem [title=" + title + ", count=" + count + ", price="
				+ price + ", totalPrice=" + totalPrice + ", orderId=" + orderId
				+ "]";
	}
	public OrderItem(String title, int count, double price, double totalPrice,
			String orderId) {
		super();
		this.title = title;
		this.count = count;
		this.price = price;
		this.totalPrice = totalPrice;
		this.orderId = orderId;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}
