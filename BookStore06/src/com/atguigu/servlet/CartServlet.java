package com.atguigu.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    BookService bs=new BookServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    //添加购物车
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//点击添加购物车时带上的图书id传过来了
		Book book = WebUtils.param2bean2(request,new Book());
		HttpSession session = request.getSession();
		/*Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			session.setAttribute("cart",cart);
		}*/
		//获取购物车的时候已经将购物车对象放到域中了
		//cart里面有allitems（所有购物项）
		Cart cart = WebUtils.getCart(request);
		//根据id从-->数据库<--获取到这本书
		//这一步一定要通过service方法
		Book one = bs.getOne(book);
		//把这本书加入购物车
		cart.addBook2Cart(one);
		//把这本书的title（书名）放进域中
		session.setAttribute("title", one.getTitle());
		//回到上一页面
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}

	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		//根据图书id删除购物项
		cart.deleteItem(request.getParameter("id"));
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
		
	}
	
	protected void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取购物车
		/*HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");*/
		Cart cart = WebUtils.getCart(request);
		//根据图书id和数量修改购物项
		//页面request请求中要带上id和count
		cart.updateCount(request.getParameter("id"), request.getParameter("count"));
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
		
	}
	//清空购物车
	protected void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取购物车
		Cart cart = WebUtils.getCart(request);
		//购物车清空
		cart.clear();
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}
	
	protected void addAjax(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//页面传过来的带id的书
		Book book = WebUtils.param2bean2(request,new Book());
		HttpSession session = request.getSession();
		//获取到购物车
		Cart cart = WebUtils.getCart(request);
		//service方法得到这本书的所有信息
		Book one = bs.getOne(book);
		//把书加入购物车
		cart.addBook2Cart(one);
		int count = cart.getTotalCount();
		String title = one.getTitle();
		//用HashMap来装图书数量以及图书title
		//通过servlet把要发送给浏览器的数据封装为map
		Map<String,Object> map=new HashMap<String, Object>();
		//服务器将商品总数和已经刚刚添加的图书返回给页面
		map.put("totalCount", count);
		map.put("title", title);
		Gson gson = new Gson();
		//把map的json传过去
		//将对象转化为json字符串
		String json = gson.toJson(map);
		response.getWriter().write(json);
	}
	protected void updateAjax(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取购物车
		/*HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");*/
		Cart cart = WebUtils.getCart(request);
		//根据图书id和数量修改购物项
		//页面request请求中要带上id和count
		cart.updateCount(request.getParameter("id"), request.getParameter("count"));
		/*String refer = request.getHeader("referer");
		response.sendRedirect(refer);*/
		String bookid = request.getParameter("id");
		CartItem item = cart.getItem(bookid);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("totalPrice", item.getTotalPrice());
		map.put("totalCount", cart.getTotalCount());
		map.put("totalMoney", cart.getTotalMoney());
		Gson gson = new Gson();
		//把map的json传过去
		//将对象转化为json字符串
		String json = gson.toJson(map);
		response.getWriter().write(json);
		
	}

}
