<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인 폼</h1>
<form action="<%=request.getContextPath()%>/memberinsert" method="post" enctype="multipart/form-data">
아이디 <input type=text name="id"><br>
암호 <input type=password name="pw"><br>
이름 <input type=text name="name"><br>
번호 <input type="tel" name="phone"><br>
이메일 <input type=text name="email"><br>
주소 <input type=text name="address"><br>
사진 <input type="file" id="imagefile" name="imagefile"><br>
<input type=submit value="로그인">
</form>
</body>
</html>