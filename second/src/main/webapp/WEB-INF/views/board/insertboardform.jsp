<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function() {
		$("#upload_btn").on('click', function(e) {
			$.ajax({
				url : '/insertboardprocess',
				data : {
					'title' : $("#title").val(),
					'contents' : $("#contents").val(),
				},
				dataType : 'JSON',
				type : 'POST',
				success : function(response) {
					location.href = "/list";
				}
			});
		});
	});
</script>

</head>
<body>
	<form>
		<table border=1>
			<tr>
				<td>제목</td>
				<td><input id="title" type=text value="${oneboard.title}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea id="contents" cols=50 rows=5>"${oneboard.contents}"</textarea></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" value="${oneboard.contents}" readonly></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="upload_btn" type=button value="글쓰기"></td>
			</tr>
		</table>
	</form>
</body>
</html>