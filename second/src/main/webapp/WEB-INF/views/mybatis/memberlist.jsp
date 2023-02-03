<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.1.min.js"></script>
<script>
$(document).ready(function(){
	
	$(".item").on('click', function(e) {
		//기본동작이벤트 처리내장 태그 처리
		e.preventDefault();
		alert($(this).attr("id"));
		$.ajax({
			url:'/othermemberinfom',
			data:{'id' : $(this).attr("id")},
			dataType: 'JSON',
			type:'GET',
			success : function(response){
				$('#inform').html(response.id+"<br>");
				$('#inform').append(response.name+"<br>");
				$('#inform').append(response.address+"<br>");
				$('#inform').append("<img src='/upload/"+response.image+"'>'<br>");
			}
		});
		
	});
});
	
</script>
</head>
<body>
	<h1>전체 회원 정보를 가져옵니다.</h1>
	<c:forEach items="${memberlist}" var="member">
		${member.id} : ${member.pw} : 
		<a id="${member.id}" class="item" href="">${member.name}</a> : ${member.address} <br>
	</c:forEach>
	<h1 id="inform"></h1>
</body>
</html>