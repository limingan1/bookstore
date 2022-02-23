package com.atguigu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Constants;
import com.atguigu.bean.Order;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderManagerServlet
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	OrderService os=new OrderServiceImpl();
       
    
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//直接查询所有订单
		List<Order> list = os.getAllOrder();
		request.setAttribute("orders", list);
		request.getRequestDispatcher("pages/manager/order_manager.jsp").forward(request, response);
	}
	
	protected void deliver(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orderid = request.getParameter("orderid");
		os.updateStatus(orderid, Constants.delivering+"");
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}

}
