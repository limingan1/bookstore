<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$("#gotopage").click(function(){
			//alert($("#pn_input").val());
			var pn=$("#pn_input").val();
			//带上链接地址以及页号
			window.location.href="${page.url}&pn="+pn;
		});
	})
	
</script>
</head>
<body>
	<div id="page_nav">
	<!-- 通过url区分是管理员的分页还是用户的分页 -->
	<!-- page类的属性是一样的,因为是同一个page类 -->
		<a href="${page.url}&pn=1">首页</a>
		<c:if test="${page.hasPrev}">
		<!-- 带上页号，调用对应的servlet方法 -->
			<a href="${page.url}&pn=${page.pageNo-1 }">上一页</a>
		</c:if>
		<!-- 如果总页数小于5，则全部显示 -->
		<c:if test="${page.totalPage<=5 }">
			<c:set var="begin" value="1" scope="page"></c:set>
			<c:set var="end" value="${page.totalPage }"></c:set>
		</c:if>
		<!-- 大于5则部分显示 -->
		<c:if test="${page.totalPage>5 }">
			<c:if test="${page.pageNo<=3 }">
			<!-- 小于三则显示前五页 -->
				<c:set var="begin" value="1" scope="page"></c:set>
				<c:set var="end" value="5"></c:set>
			</c:if>
			<!-- 大于3则显示前两页和后两页 -->
			<c:if test="${page.pageNo>3 }">
				<c:set var="begin" value="${page.pageNo-2 }" scope="page"></c:set>
				<c:set var="end" value="${page.pageNo+2 }"></c:set>
			</c:if>	
			<!-- 如果当前页的后两页大于总页数，则取总页数 -->
			<c:if test="${page.pageNo+2>page.totalPage }">
			<!-- 从总页数减四到总页数，如总页数是 10，当前页是9或10，则显示6、7、8、9、10 -->
				<c:set var="begin" value="${page.totalPage-4 }" scope="page"></c:set>
				<c:set var="end" value="${page.totalPage }"></c:set>
			</c:if>
		</c:if>
		<c:forEach begin="${begin }" end="${end }" var="number">
		<!-- 如果当前页number和页号一样，则把当前页号这个数字变为蓝色，并加中括号 -->
			<c:if test="${page.pageNo==number }">
				<span style="color:blue;">【${page.pageNo }】</span>
			</c:if>
			<c:if test="${page.pageNo!=number }">
			<!-- 其他不是当前页号的下划线数字号的链接带上URL和页号 -->
				<a href="${page.url}&pn=${number }">${number}</a>
			</c:if>
		</c:forEach>
		<c:if test="${page.hasNext}">
		<!-- 传pn，页号+1 -->
			<a href="${page.url}&pn=${page.pageNo+1 }">下一页</a>
		</c:if>
		<a href="${page.url}&pn=${page.totalPage }">末页</a>
		<!-- input标签默认为text（文本框 -->）
		共${page.totalPage }页，${page.totalCount }条记录 到第<input value="${page.pageNo}" name="pn" id="pn_input"/>页
		<!-- 值为确定的按钮，为其id绑定点击事件 -->
		<input type="button" value="确定" id="gotopage">
		</div>
	
</body>
</html>