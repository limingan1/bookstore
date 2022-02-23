package com.atguigu.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.code.kaptcha.Constants;

/**
 * 处理所有和用户有关系的请求 Servlet implementation class UserServlet
 * 抽取出BaseServlet以后
 * UserServlet里面只需要编写相应的处理逻辑即可
 */
public class UserServlet extends BaseServlet{
	private UserService us = new UserServiceImpl();
	//方法名(request,response)
	
	/*protected void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//编写修改用户密码的方法
	}*/
	
	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");*/
		//从page中取出名为code的标签里面的值-->code
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		//constants里面的随机验证码里面的值-->sessioncode
		String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if(!sessionCode.equals(code)){
			//把验证码错误命名为msg放到域中
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			return;
		}
		//获取从页面来user对象
		User user = WebUtils.param2bean(request,new User());
		// 用户注册,数据库中无数据即可注册,
		boolean b = us.regist(user);
		if (b) {
			// 注册成功 返回成功页面 重定向
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/regist_success.jsp");

		} else {
			// 注册失败 返回注册页面，重新注册 转发
			request.setAttribute("msg", "用户已经存在");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
					request, response);
		}
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");*/
		User user2 = WebUtils.param2bean2(request, new User());
		//调用对象us的login方法，返回一个user类
		User user = us.login(user2);
		//创建一个session
		HttpSession session = request.getSession();
		//把user放在session域中
		session.setAttribute("user", user);
		//判断数据库是否有该用户
		if (user == null) {
			// 登陆失败(域中无该user类) 返回登陆页面即可 转发
			request.setAttribute("msg", "用户密码错误");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);
		} else {
			// 登陆成功 返回成功页面 重定向
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/login_success.jsp");

		}
		
	}

	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//使session无效化，也就是没有user对象放到域中，us的login方法返回空对象，user==null
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	
	protected void checkUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//页面传来的user对象
		User user = WebUtils.param2bean2(request, new User());
		boolean b = us.checkUser(user);
		//直接在边上输出用户名是否可用
		if(b){
			response.getWriter().write("用户名可用");
		}else{
			response.getWriter().write("用户名已存在!");
		}
		
		
	}

}
