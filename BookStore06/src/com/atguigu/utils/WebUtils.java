package com.atguigu.utils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bean.Cart;
import com.atguigu.bean.User;

public class WebUtils {
	/**
	 * 传入request对象，将request中的数据封装成对象
	 * @param request
	 * @param t
	 * @return
	 */
	public static<T> T param2bean(HttpServletRequest request,T t){
		//封装对象，并返回
		//1、获取所有声明的属性
		Field[] fields = t.getClass().getDeclaredFields();
		
		//2、每个属性都有name值，属性名
		for (Field field : fields) {
			//获取属性名
			String name = field.getName();
			//在request中获取相应的属性值
			String value = request.getParameter(name);
			try {
				BeanUtils.setProperty(t, name, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//封装对象setAttrName()
		}
		return t;
	}
	//T就是类的泛型
	public static<T> T param2bean2(HttpServletRequest request,T t){
		//populate将map中的键值对，直接映射到javaBean(类)中
		//getParameterMap包含前端请求参数和请求参数值的映射关系
		Map map = request.getParameterMap();
		try {
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	//获取购物车
	public static Cart getCart(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		//获取到购物车，强转为Cart型
		Cart cart = (Cart) session.getAttribute("cart");
		//还要用cart对象 Cart cart
		if(cart==null){
			//新建一个购物车
			cart=new Cart();
			//把新建的购物车放到域中
			session.setAttribute("cart",cart);
		}
		return cart;
	}
	//获取登录用户
	public static User getLoginUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		return (User) session.getAttribute("user");
	}

}
