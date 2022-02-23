package com.atguigu.utils;
//Connection类
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取和释放数据库连接
 * 
 * @author lfy
 * 
 */
public class JDBCUtils {

	private static DataSource ds = new ComboPooledDataSource("webDataSource");
	//private static Map<Long, Connection> conns = new HashMap<>();

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	//jdbcUtils的getConnection方法
	public static Connection getConnection() {
		/*long id = Thread.currentThread().getId();
		System.out.println("JDBCUtils中的线程号："+id);*/
		//获取当前线程的连接
		//Connection connection = conns.get(Thread.currentThread().getId());
		Connection connection=null;
			try {
				connection = ds.getConnection();
				//把链接保存在map中
				//conns.put(id, connection);
				//connection.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return connection;
	}


	/**
	 * 释放数据库连接
	 * @param connection
	 */
	public static void releaseConnection() {
		//方法()里可以直接Connection connection,去掉下面这行
		Connection connection=getConnection();
		try {
			if (connection != null)
				connection.close();
				//conns.remove(Thread.currentThread().getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
