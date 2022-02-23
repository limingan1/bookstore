package com.atguigu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

/**
 * Servlet implementation class BookClientServlet
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookService bs=new BookServiceImpl();
    
	protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("bcs");
		System.out.println("分页啦");
		String pn = request.getParameter("pn");
		//封装部分图书
		Page<Book> page = bs.getPage(pn, "4");
		//用分页方法
		page.setUrl("client/BookClientServlet?method=page");
		//把page类放到域中，供booklist.jsp使用
		request.setAttribute("page", page);
		//返回首页booklist.jsp，并让其显示客户端分页数据
		request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request, response);
	}
	protected void pageByPrice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String max = request.getParameter("max");
		String min = request.getParameter("min");
		String pn = request.getParameter("pn");
		//System.out.println("价： min-->"+min+"max-->"+max);
		//用getPageByPrice方法
		Page<Book> page = bs.getPageByPrice(pn, "4", min, max);
		page.setUrl("client/BookClientServlet?method=pageByPrice&max="+max+"&min="+min);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request, response);
		
	}
}
