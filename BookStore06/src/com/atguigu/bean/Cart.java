package com.atguigu.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable{
	//key value LinkedHashMap可以自动排列
	//购物项和图书id一一对应，图书id是隐含属性
	//引入CartItem类，cart类可以用CartItem类的一些属性
	private Map<Integer, CartItem> items=new LinkedHashMap<>();
	/*public Map<Integer, CartItem> getItems() {
		return items;
	}*/
	//该方法为int型，返回一个整数
	//获取所有购物项的购买总图书数
	public int getTotalCount() {
		//封装所有购物项
		List<CartItem> list = getAllItems();
		//数量初值为0
		int count=0;
		//CartItem是类型  list是总的购物项  cartItem是遍历变量
		for (CartItem cartItem : list) {
			//把每一个购物项的数量加起来
			//调用CartItem的getCount方法
			count+=cartItem.getCount();
		}
		return count;
	}
	public double getTotalMoney() {
		//封装所有购物项
		//因为是不同方法，所以需要重新封装
		List<CartItem> list = getAllItems();
		//初始总金额为0
		BigDecimal money = new BigDecimal(0.0+"");
		//遍历每一个购物项
		for (CartItem cartItem : list) {
			//money+=cartItem.getTotalPrice();
			//把每一个购物项的总金额加起来，就是购物车的总金额
			money=money.add(new BigDecimal(cartItem.getTotalPrice()+""));
		}
		return money.doubleValue();
	}
	//保存所有的购物项
	//因为方法类型为List，所以返回一个ArrayList<>();
	public List<CartItem> getAllItems(){
		//用collection封装CartItem类
		//返回所有购物项
		Collection<CartItem> values = items.values(); 
		return new ArrayList<>(values);
	}
	//关于购物项的数量
	//把图书加入购物车的过程
	//括号里为BookService传入的book对象
	public void addBook2Cart(Book book){
		//根据图书id获取CartItem（购物项）对象！！！！！！！！！！！！！！！！！！
		//根据一个图书id只能获取到一种书
		CartItem item = items.get(book.getId());
		//如果没有这个购物项（根据图书id）
		if(item==null){
			//新建一个购物项
			CartItem cartItem = new CartItem();
			//根据图书id把该书放入
			//也就是说已经根据图书id获取到这本书了
			//在setBook之前一定要先经过数据库
			//根据请求过来的图书id，调用bs的getOne方法得到该书的全部信息！！！！！！！
			//再把这本书加进购物车里
			cartItem.setBook(book);
			//设置其数量为1，即一开始的数量！！！！！！！！！！！！！
			cartItem.setCount(1);
			//cartItem根据图书id放进items
			//放进去后第二次肯定就有了
			//购物项和图书id一一对应
			items.put(book.getId(), cartItem);
			//如果有这个购物项的话（肯定是原先添加过这本书）
		}else{
			//对应图书id，图书数量+1
			item.setCount(item.getCount()+1);
		}
	}
	//根据id删除购物项
	public void deleteItem(String bookid){
		int id = Integer.parseInt(bookid);
		//调用HashMap的remove方法
		items.remove(id);
	}
	//传入图书id和要修改的图书数量
	//是在购物车里显示的，所以在购物车里定义方法
	public void updateCount(String bookid,String count){
		//一开始设置某一购物项的数量为1
		int c=1;
		int id=0;
		try {
			//转型，如果C<=0，则让它为1
			//把方法括号里第二个变量赋给c
			c = Integer.parseInt(count);
			c=c>0?c:1;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//转型
			//把方法括号里第一个变量赋给id
			id = Integer.parseInt(bookid);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//根据id获得购物项
		//要根据图书id来改某一购物项的id
		CartItem item = items.get(id);
		//如果根据id获得的购物项不为空，则修改其数量
		if(item!=null)
			item.setCount(c);
	}
	//清空购物车
	public void clear(){
		items.clear();
	}
	public CartItem getItem(String bookid){
		int parseInt = Integer.parseInt(bookid);
		CartItem item = items.get(parseInt);
		return item;
		
	}
}