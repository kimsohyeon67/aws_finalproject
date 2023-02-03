<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<h1>파일업로드 결과</h1>
	<h3>작성자 : ${dto.name}</h3>
	<h3>설명 : ${dto.description } 업로드 파일명1 :${dto.file1.originalFilename} </h3>
	<h3>업로드 파일명2 : ${dto.file2.originalFilename}</h3>
	
	
</body>
</html>