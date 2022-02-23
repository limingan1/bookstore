package com.atguigu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.atguigu.bean.User;
import com.atguigu.utils.WebUtils;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter已启动！");
		//把方法括号里的request对象转为HttpServletRequest型
		//再把request赋值给HttpServletRequest创建的对象
		HttpServletRequest req=(HttpServletRequest) request;
		User user = WebUtils.getLoginUser(req);
		if(user==null){
			request.setAttribute("msg", "请先登录！");
			request.getRequestDispatcher("pages/user/login.jsp").forward(request, response);
		}else{
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
