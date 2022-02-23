package com.atguigu.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

public class BookDaoTest {
	BookDao bd=new BookDaoImpl();
	BookService bs=new BookServiceImpl();
	@Test
	public void test1(){
		List<Book> list = bd.getAllBook();
		System.out.println(list);
	}
	@Test
	public void test2(){
		Book book=new Book(8,"qw","黎明安",1.00,2,3,null);
		//System.out.println(book);
		boolean b = bd.addBook(book);
		System.out.println(b);
	}
	@Test
	public void test3(){
		Book book=new Book();
		book.setId(1);
		boolean delBook = bd.delBook(book);
		System.out.println(delBook);
	}
	@Test
	public void test4(){
		Book book=new Book(2,"we","lma",1,2,3,null);
		boolean updateBook = bd.updateBook(book);
		System.out.println(updateBook);
	}
	@Test
	public void test5(){
		Book book=new Book();
		book.setId(2);
		Book searchBook = bd.searchBook(book);
		System.out.println(searchBook);
	}
	@Test
	public void test6(){
		Page<Book> page = bs.getPage("2", "4");
		System.out.println(page.getPageData());
		System.out.println(page.getPageNo());
		System.out.println(page.getPageSize());
		System.out.println(page.getTotalCount());
		System.out.println(page.getTotalPage());
	}
	@Test
	public void test7(){
		//Page<Book> page = bs.getPage("2", "4");
		Page<Book> page = bs.getPageByPrice("1", "4", "0", "1");
		System.out.println(page.getPageData());
		System.out.println(page.getPageNo());
		System.out.println(page.getPageSize());
		System.out.println(page.getTotalCount());
		System.out.println(page.getTotalPage());
	}
}
