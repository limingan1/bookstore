package com.atguigu.test;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.atguigu.bean.User;

public class BeanUtilsTest {
	@Test
	public void test1(){
		User user = new User();
		System.out.println("未设置："+user);
		try {
			BeanUtils.setProperty(user, "username", "小明");
			System.out.println("设置之后："+user);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}
	@Test
	public void test2(){
		Student stu=new Student();
		System.out.println("未设置："+stu);
		try {
			BeanUtils.setProperty(stu, "age", "127");
			System.out.println("设置后："+stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
