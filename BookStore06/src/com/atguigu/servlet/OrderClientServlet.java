package com.atguigu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Constants;
import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;
import com.atguigu.bean.User;
import com.atguigu.service.OrderItemService;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderItemServiceImpl;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class OrderClientServlet
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	OrderService os=new OrderServiceImpl();
	OrderItemService ois=new OrderItemServiceImpl();
    //购物车结算操作   
	protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*long id = Thread.currentThread().getId();
		System.out.println("servelt中的线程号："+id);*/
		HttpSession session = request.getSession();
		//User loginUser = (User) session.getAttribute("user");
		//获取登录用户
		User loginUser = WebUtils.getLoginUser(request);
		/*if(loginUser!=null){
			Cart cart = WebUtils.getCart(request);
			String orderId = os.checkout(cart, loginUser);
			session.setAttribute("orderId", orderId);
			response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
		}else{
			request.setAttribute("msg", "此操作需要登录");
			request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
		}*/
		//获取购物车
		Cart cart = WebUtils.getCart(request);
		//通过orderService的对象的checkout方法传入用户以及购物车得到订单号
		//这个订单号是唯一对应的
		String orderId = os.checkout(cart,loginUser);
		//把orderId放入域中，供checkout.jsp页面使用
		session.setAttribute("orderId",orderId);
		response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
		
	}
	//显示某一用户的订单
	protected void list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//首先，获取登录用户
		//得到了用户的Id
		//只要当前用户还没注销，就还有用户id
		User user = WebUtils.getLoginUser(request);
		//用list来封装所有订单
		List<Order> list=null;
		try {
			//通过用户id获取到用户的所有订单
			list = os.getMyOrders(user.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//把所有订单放入域中
		//查看我的订单的时候可以直接用
		request.setAttribute("orders", list);
		request.getRequestDispatcher("pages/order/order.jsp").forward(request, response);
	}
	
	protected void received(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取订单号
		String orderid = request.getParameter("orderid");
		//通过订单号改变订单状态
		os.updateStatus(orderid, Constants.delivered+"");
		//返回上一页面
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
		
	}
	
	protected void listOrderItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orderId = request.getParameter("orderId");
		List<OrderItem> orderItems = ois.getOrderItems(orderId);
		//System.out.println(orderItems);
		request.setAttribute("orderItems",orderItems);
		request.getRequestDispatcher("pages/order/order_item.jsp").forward(request, response);
	}
}
