<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/jquery-3.6.1.min.js"></script>
<style>
#chatarea {
	background-color: lime;
	border: 2px solid black
}
</style>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	별명 :
	<input type=text id="nickname" value="${param.id }">
	<input type=button id="enterbtn" value="입장">
	<input type=button id="exitbtn" value="퇴장">
	<h1>채팅</h1>
	<div id="chatarea">
		채팅내용표시<br>
	</div>
	전송할 메시지:
	<input type=text id=message>
	<input type=button id=sendbtn value="전송">
	<script>
	$(function() {
		let websocket;
		$("#enterbtn").on('click',function(){
			websocket = new WebSocket("ws://localhost:8085/ws");
			websocket.onopen = open;
			websocket.onclose = close;
			//서버가 응답한 내용 수신
			websocket.onmessage = message;
		});
		
		//서버로 송신
		$("#sendbtn").on('click',function(){ 
			let nickname = $("#nickname").val();
			let sendmessage = $("#message").val();
			
			websocket.send(nickname + ": " +sendmessage);
		
		});
		
		const open = () => {
			console.log("웹소켓 연결 성공");
		};
		const close =() => {
			console.log("웹소켓 해제 성공");
		};
		const message = (event) => {
			$("#chatarea").append(event.data + "<br>");
			console.log("서버로부터 메시지 수신 성공");
		};
	})
	</script>
</body>
</html>