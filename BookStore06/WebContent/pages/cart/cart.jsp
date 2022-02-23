<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@include file="/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".delBtn").click(function(){
			var textEle=$(this).parents("tr").children(":first").text();
			if(!confirm("确认删除【"+textEle+"】吗？"))
				return false; 
		});
		/* 只要这个标签的count值一改，就绑定事件 */
		/* $(".changeinput").change(function(){
			//alert("哦");
			var count=$(this).val();
			var id=$(this).attr("updateid");
			//alert(count+"-->"+id);
			location.href="client/CartServlet?method=update&id="+id+"&count="+count;
		}); */
		$(".changeinput").change(function(){
			//获取到当前标签的值
			var count=$(this).val();
			//获取图书id
			var id=$(this).attr("updateid");
			$.getJSON("client/CartServlet?method=updateAjax&id="+id+"&count="+count,function(data){
				//alert(data.totalPrice);
				$("#itemTotalPrice").text(data.totalPrice);
				$(".b_count").text(data.totalCount);
				$(".b_price").text(data.totalMoney);
				
			});
		});
		$("#clearBtn").click(function(){
			if(!confirm("确认清空购物车吗？"))
				return false; 
		});
		
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/include/user-info.jsp" %>
	</div>
	
	<div id="main">
		<c:if test="${empty cart.allItems}">
			<div style="position: absolute;left:500px;top:300px">
				购物车为空，赶紧<a href="index.jsp">购买</a>吧
			</div>
		</c:if>
		<c:if test="${!empty cart.allItems}">
			<table>
				<tr>
					<td>商品名称</td>
					<td>数量</td>
					<td>单价</td>
					<td>金额</td>
					<td>操作</td>
				</tr>	
			<!-- 获取购物车的时候servlet对应方法已经将购物车类放在域中了，它要跳转的页面可以用这个类的属性 -->
			<!-- 	cart.allItems（这是一个属性）是所有购物项，一个图书id对应一个购物项	 -->
			<!-- getAllItems是一个getter方法，get后面的是属性名 -->
			<!-- 这是在类中定义的getter方法，获取到了所有购物项的信息 -->
				<c:forEach items="${cart.allItems}" var="item">
					<tr>
						<td>${item.book.title }</td>
						<td>
						<!-- 这个input标签的值为item.count，自定义的属性名为图书的id -->
							<input updateid="${item.book.id }" class="changeinput" style="width: 30px" type="text" name="count" value="${item.count }"/>
						</td>
						<td>${item.book.price }</td>
						<td id="itemTotalPrice">${item.totalPrice }</td>
						<td><a  class="delBtn" href="client/CartServlet?method=delete&id=${item.book.id }">删除</a></td>
					</tr>		
				</c:forEach>		
			</table>
				
			<div class="cart_info">
			<!-- 这里用到了cart的两个属性totalCount和totalMoney -->
				<span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount }</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${cart.totalMoney }</span>元</span>
				<span class="cart_span"><a id="clearBtn" href="client/CartServlet?method=clear">清空车</a></span>
				<span class="cart_span"><a href="OrderClientServlet?method=checkout">去结账</a></span>
			</div>
		</c:if>
		
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>