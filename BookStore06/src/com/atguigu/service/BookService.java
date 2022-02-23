package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;

public interface BookService {
	public boolean add(Book book);
	public boolean del(Book book);
	public boolean update(Book book);
	public List<Book> getAll();
	public Book getOne(Book book);
	public Page<Book> getPage(String pageNo,String pageSize);
	public Page<Book> getPageByPrice(String pageNo,String pageSize,String minPrice,String maxPrice);
}
