package com.atguigu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class BookManagerServlet
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookService bs=new BookServiceImpl();
       
	 //显示分页图书的时候已经用Page类将图书信息封装在request域中了
    //前端页面可以直接用
    protected void page(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    		System.out.println("分页啦");
    		//获取到页面来的页号,默认是第一页，也就是首页
    		String pn = request.getParameter("pn");
    		//调用bs对象的分页方法，封装部分图书
    		Page<Book> page = bs.getPage(pn, "4");
    		page.setUrl("manager/BookManagerServlet?method=page");
    		//封装所有页用页面(page)封装的图书(book)放到域中
    		request.setAttribute("page", page);
    		//返回管理端页面，并让其显示分页图书数据
			request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    		
    }
   
	/*protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			List<Book> list = bs.getAll();
			//封装所有图书到request域中
			request.setAttribute("list", list);
			request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}*/

	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//从页面过来的book对象
		Book book = WebUtils.param2bean2(request, new Book());
		/*可以尝试输出这本书
		System.out.println(book);*/
		//添加这本书
		bs.add(book);
		response.sendRedirect(request.getContextPath()+"/manager/BookManagerServlet?method=page");
	}

	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//带页号的原因是因为获取图书或者删除、修改图书操作完成后回到该图书所在的页面
		String param=request.getParameter("pn");
		System.out.println("当前停留的页码"+param);
		Book book = WebUtils.param2bean2(request, new Book());
		//System.out.println(book);
		bs.del(book);
		//删除完之后返回list页面并且带上当前已删除图书所在的页号，回到该页
		response.sendRedirect(request.getContextPath()+"/manager/BookManagerServlet?method=page&pn="+param);
	}
	//点击修改链接，调用该方法
	//获取某本书的信息，修改图书信息的时候用的
	protected void getBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("页码："+request.getParameter("pn"));
		//从上一个页面来的请求信息，这是带上了图书id的书哦
		Book book = WebUtils.param2bean2(request, new Book());
		//System.out.println("====="+book);
		//System.out.println(book);
		//根据图书id获取到这本书
		//调用service方法
		Book one = bs.getOne(book);
		//把这本书放到域中
		//供book_edit.jsp使用
		request.setAttribute("book", one);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取图书页号
		String pn=request.getParameter("pn");
		//从上一个页面来的请求信息，这是带上了图书id的哦
		Book book = WebUtils.param2bean2(request, new Book());
		//添加图书的话是一开始没有图书id和页号的
		if(book.getId()!=0){
			bs.update(book);
		}
		else{
			bs.add(book);
		}
		//修改完之后返回list页面并且带上当前已修改图书所在的页号，回到该页
		response.sendRedirect(request.getContextPath()+"/manager/BookManagerServlet?method=page&pn="+pn);
	}
}
