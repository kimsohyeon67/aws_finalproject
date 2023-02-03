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
	<form action="">
		<table border=1>
			<tr><td>번호</td><td><input type=text value="${oneboard.seq}" readonly></td></tr>
			<tr><td>제목</td><td><input type=text value="${oneboard.title}" ></td></tr>
			<tr><td>내용</td><td><textarea cols=50 rows=5 >"${oneboard.contents}"</textarea></td></tr>
			<tr><td>작성자</td><td><input type=text value="${oneboard.writer}" readonly></td></tr>
			<tr><td>조회수</td><td><input type=text value="${oneboard.viewcount}" readonly></td></tr>
			<tr><td>작성시간</td><td><input type=text value="${oneboard.viewcount}" readonly></td></tr>
			<tr><td></td><td><input id="modify_btn" type=button value="수정" ><input id="remove_btn" type=button value="삭제" ></td></tr>
		</table>
	</form>
</body>
</html>