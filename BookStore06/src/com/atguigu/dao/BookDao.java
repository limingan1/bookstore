package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.Book;

public interface BookDao {
	public List<Book> getAllBook();
	public boolean addBook(Book book);
	public boolean delBook(Book book);
	public boolean updateBook(Book book);
	public Book searchBook(Book book);
	public List<Book> getPageList(int index,int size);
	public int getTotalCount();
	public int getTotalCountByPrice(double minPrice,double maxPrice);
	public List<Book> getPageByPrice(int index, int size,double minPrice,double maxPrice);
	//public void updateStockAndSales(Integer bookid,Integer stock,Integer sales);
	
}
