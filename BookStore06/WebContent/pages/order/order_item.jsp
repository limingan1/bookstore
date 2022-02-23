<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<%@include file="/include/base.jsp" %>
</head>
<body>
<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单详情</span>
			<%@include file="/include/user-info.jsp" %>
	</div>
	<div id="main">		
			<table>
				<tr>
					<td>书名</td>
					<td>数量</td>
					<td>单价</td>
					<td>总价</td>
				</tr>		
				<c:forEach items="${requestScope.orderItems}" var="orderItem">
					<tr>
						<td>${orderItem.title}</td>
						<td>${orderItem.count}</td>
						<td>${orderItem.price}</td>
						<td>${orderItem.totalPrice}</td>
					</tr>		
				</c:forEach>		
			</table>		
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>