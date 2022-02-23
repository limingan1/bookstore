<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@include file="/include/base.jsp" %>
<!-- 使用js解析字符串，将数据放到页面相应位置 -->
<script type="text/javascript">
	$(function(){
		
		$(".addCartBtn").click(function(){
			//获取当前按钮自定义的id值，用attr
			var bookId=$(this).attr("updateId");
			//getJson将响应的字符串直接转为json对象
			$.getJSON("client/CartServlet?method=addAjax&id="+bookId,function(data){
				//alert(data.title);
				$("#totalCountTip").text("您当前购物车中有"+data.totalCount+"件商品");
				$("#bookTitleTip").text("您刚刚将【"+data.title+"】加入了购物车");
			})
		})
	})
</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<!-- 这里是操作菜单 -->
			<%@include file="/include/user-info.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/BookClientServlet" method="get">
					<input name="method" value="pageByPrice" type="hidden" />
					价格：<input type="text" name="min" value="${param.min }"> 元 - 
					<input type="text" name="max" value="${param.max }"> 元 
					<button>查询</button>
				</form>
			</div>
			<div style="text-align: center">
				<%-- <span>您的购物车中有<c:out value="${cart.totalCount }" default="0"></c:out>件商品</span> --%>
				<div>
					
					<!-- 这是从session中取的数据 -->
					<span id="totalCountTip">您的购物车中有
					<c:out value="${cart.totalCount}" default="0"></c:out>
					件商品</span>
					
				</div>				
				<%-- <c:if test="${!empty title }">
					<div>
						您刚刚将<span style="color: red">${title }</span>加入到了购物车中
						<c:remove var="title" scope="session"/>
					</div>
				</c:if> --%>
				<div id="bookTitleTip"></div>
			</div>
			
			
			
			<!-- page.pageData是分页数据，book是遍历变量名 -->
			<c:forEach items="${page.pageData }" var="book">
				<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath }" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.title }</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author }</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">${book.price }</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales }</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock }</span>
					</div>
					<div class="book_add">
					<%-- <!-- 必须传入该图书的id！！！！！！！！！！！ -->
					<!-- 根据图书id就获取到了cartItem类的全部信息 -->
					<!-- 在结账的时候把cartItem的部分信息赋值给orderItem的toString方法 -->
						<a style="color:blue" href="client/CartServlet?method=add&id=${book.id }">加入购物车</a> --%>
						<button updateId="${book.id}" class="addCartBtn">加入购物车</button>
					</div>
				</div>
			</div>
			</c:forEach>
			
		</div>
		
		<%@include file="/include/page.jsp" %>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>