package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.BookDao;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		String sql="select id,title,author,price,sales,stock,img_path imgPath from bs_book";
		//返回一系列图书
		 return getBeanList(sql);
	}

	@Override
	
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		String sql="insert into bs_book(title,author,price,sales,"
					+"stock,img_path) values(?,?,?,?,?,?)";
		int update = update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),
				book.getSales(),book.getStock(),book.getImgPath()
				);
		return update>0;
	}

	@Override
	public boolean delBook(Book book) {
		// TODO Auto-generated method stub
		String sql="delete from bs_book where id=?";
		int update = update(sql,book.getId());
		return update>0;
	}

	@Override
	//修改该书除id外所有信息
	public boolean updateBook(Book book) {
		// TODO Auto-generated method stub
		String sql="update bs_book set title=?,author=?,price=?"
				+ ",sales=?,stock=?,img_path=? where id=?";
		int update = update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),
				book.getSales(),book.getStock(),book.getImgPath(),book.getId());
		return update>0;
	}

	@Override
	//通过图书id查询一本图书
	public Book searchBook(Book book) {
		// TODO Auto-generated method stub
		String sql="select id,title,author,price,sales,"
				+ "stock,img_path imgPath from bs_book where id=?";
		return getBean(sql,book.getId());
		
	}

	@Override
	//按索引和每页条数显示图书
	public List<Book> getPageList(int index, int size) {
		// TODO Auto-generated method stub
		//从哪到哪 
		String sql="select id,title,author,price,sales,stock,"
				+ "img_path imgPath from bs_book limit ?,?";
		List<Book> list = getBeanList(sql, index,size);
		return list;
	}
	//按价格区间得到页面
	public List<Book> getPageByPrice(int index, int size,double minPrice,double maxPrice) {
		// TODO Auto-generated method stub
		String sql="select id,title,author,price,sales,stock,"
				+ "img_path imgPath from bs_book where price between ? and ? limit ?,?";
		List<Book> list = getBeanList(sql,minPrice,maxPrice, index,size);
		return list;
	}
	@Override
	//得到总记录数
	public int getTotalCount() {
		// TODO Auto-generated method stub
		//总记录数
		String sql="select count(*) from bs_book";
		Object object = getSingleValue(sql);
		int parseInt=0;
		try {
			parseInt = Integer.parseInt(object.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parseInt;
	}

	@Override
	public int getTotalCountByPrice(double minPrice, double maxPrice) {
		// TODO Auto-generated method stub
		//图书类的总数在价格。。到。。
		String sql="select count(*) from bs_book where price between ? and ?";
		Object object = getSingleValue(sql, minPrice,maxPrice);
		int i=0;
		try {
			i = Integer.parseInt(object.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	//@Override
	/*public void updateStockAndSales(Integer bookid, Integer stock, Integer sales) {
		// TODO Auto-generated method stub
		
	}*/

}
