<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function() {
		$("#write_btn").on('click', function(e) {
			location.href="/insertboard";
		});
	});
</script>
</head>
<body>
	<h1>${param.page}</h1>
	<input id="write_btn" type="button" value="글쓰기">
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>

		<c:forEach items="${list}" var="board">
			<tr>
				<td>${board.seq}</td>
				<td><a href="oneboard?seq=${board.seq}">${board.title}</a></td>
				<td>${board.writer}</td>
			</tr>
		</c:forEach>
	</table>
	<%
	int totalcount = (int) request.getAttribute("totalboard");
	int totalpage = totalcount % 3 == 0 ? totalcount / 3 : totalcount / 3 + 1;

	for (int i = 1; i <= totalpage; i++) {
	%>
	<a href="boardlist?page=<%=i%>"><%=i%>페이지 </a>
	<%
	}
	%>
</body>
</html>