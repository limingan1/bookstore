<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<%@include file="/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<%@include file="/include/book-manager.jsp" %>
		</div>
		
		<div id="main">
		
			<form action="manager/BookManagerServlet" method="post">
			<!-- 修改和添加共用update方法 -->
				<input name="method" value="update" type="hidden"/>
				 <!-- 把点击修改链接传过来的图书id和页号pn隐藏起来，如果是修改图书信息的话则有id和pn --> 
				<input name="id" value="${book.id}" type="hidden"/>
			<!-- 	这里应该是（上一个request）没有把page放在域中 -->
				<input name="pn" value="${param.pn}" type="hidden"/>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
					<!-- input标签名字随便取 -->
					<!-- 如果是修改的话，页面是能获取到图书的，value会有值 -->
						<td><input name="title" type="text" value="${book.title }"/></td>
						<td><input name="price" type="text" value="${book.price }"/></td>
						<td><input name="author" type="text" value="${book.author }"/></td>
						<td><input name="sales" type="text" value="${book.sales}"/></td>
						<td><input name="stock" type="text" value="${book.stock}"/></td>
						<td><input name="book_amount" type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>