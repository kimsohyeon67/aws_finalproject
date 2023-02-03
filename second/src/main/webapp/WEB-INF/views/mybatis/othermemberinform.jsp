<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${otherinform.name}님의 정보입니다 (관리자만 허용)</h1>
<h3>아이디 : ${otherinform.id}<br></h3>
<h3>이메일 : ${otherinform.email}<br></h3>
<h3>폰 : ${otherinform.phone}<br></h3>
<h3>주소 : ${otherinform.address}<br></h3>
<h3>사진 : <image src="/upload/${otherinform.image}"><br></h3>


</body>
</html>