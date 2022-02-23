package com.atguigu.service.impl;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;

public class BookServiceImpl implements BookService{
	BookDao bd=new BookDaoImpl();
	@Override
	public boolean add(Book book) {
		// TODO Auto-generated method stub
	
		return bd.addBook(book);
	}

	@Override
	public boolean del(Book book) {
		// TODO Auto-generated method stub
		return bd.delBook(book);
	}

	@Override
	public boolean update(Book book) {
		// TODO Auto-generated method stub
		/*long id = Thread.currentThread().getId();
		System.out.println("bookDao中的线程号："+id);*/
		return bd.updateBook(book);
	}

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		return bd.getAllBook();
	}

	@Override
	//查询某一本图书
	public Book getOne(Book book) {
		// TODO Auto-generated method stub
		return bd.searchBook(book);
	}

	@Override
	//传入页号和页面书目条数
	public Page<Book> getPage(String pageNo, String pageSize) {
		// TODO Auto-generated method stub
		// 1、将用户传入的数据先封装部分
		Page<Book> page = new Page<Book>();
		// 将用户传入的数据转型并封装，设置默认值
		int pn=1;
		//默认条数为4
		int ps=page.getPageSize();
		try {
			//在page类中，pn和ps都为整型
			//转为整型
			pn = Integer.parseInt(pageNo);
			ps = Integer.parseInt(pageSize);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//设置每一页的条数，默认是4
		page.setPageSize(ps);
		//用对象bd的getTotalCount方法得到总记录数
		int totalCount = bd.getTotalCount();
		//设置总记录数
		page.setTotalCount(totalCount);
		//设置页号
		page.setPageNo(pn);
		//按索引和每页条数显示图书
		List<Book> list = bd.getPageList(page.getIndex(), page.getPageSize());
		//把List封装的图书设置为页面数据
		page.setPageData(list);
		return page;
	}

	@Override
	public Page<Book> getPageByPrice(String pageNo, String pageSize,
			String minPrice, String maxPrice) {
		// TODO Auto-generated method stub
		double min=0.0;
		double max=Double.MAX_VALUE;
		try {
			min = Double.parseDouble(minPrice);
			max = Double.parseDouble(maxPrice);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page<Book> page = new Page<Book>();
		int pn=1;
		int ps=page.getPageSize();
		try {
			pn = Integer.parseInt(pageNo);
			ps = Integer.parseInt(pageSize);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2、将页码以及页面大小设置进入page对象
				// 按照价格区间获取总记录数
		int count = bd.getTotalCountByPrice(min, max);
		//设置价格区间获取的总记录数
		page.setTotalCount(count);
		page.setPageSize(ps);
		page.setPageNo(pn);
		List<Book> list = bd.getPageByPrice(page.getIndex(), page.getPageSize(), min, max);
		page.setPageData(list);
		return page;
		//bd.getPageByPrice(index, size, min, max);
		
	}
	
}
