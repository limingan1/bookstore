package com.atguigu.test;

import java.sql.Connection;

import org.junit.Test;

import com.atguigu.utils.JDBCUtils;

public class JDBCUtilsTest {
	
	@Test
	public void getConnection(){
		//因为是静态方法，直接-->类.方法
		Connection connection = JDBCUtils.getConnection();
		System.out.println(connection);
		JDBCUtils.releaseConnection();
	}
}
