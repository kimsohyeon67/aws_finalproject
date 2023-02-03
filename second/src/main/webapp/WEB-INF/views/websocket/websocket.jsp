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
	���� :
	<input type=text id="nickname" value="${param.id }">
	<input type=button id="enterbtn" value="����">
	<input type=button id="exitbtn" value="����">
	<h1>ä��</h1>
	<div id="chatarea">
		ä�ó���ǥ��<br>
	</div>
	������ �޽���:
	<input type=text id=message>
	<input type=button id=sendbtn value="����">
	<script>
	$(function() {
		let websocket;
		$("#enterbtn").on('click',function(){
			websocket = new WebSocket("ws://localhost:8085/ws");
			websocket.onopen = open;
			websocket.onclose = close;
			//������ ������ ���� ����
			websocket.onmessage = message;
		});
		
		//������ �۽�
		$("#sendbtn").on('click',function(){ 
			let nickname = $("#nickname").val();
			let sendmessage = $("#message").val();
			
			websocket.send(nickname + ": " +sendmessage);
		
		});
		
		const open = () => {
			console.log("������ ���� ����");
		};
		const close =() => {
			console.log("������ ���� ����");
		};
		const message = (event) => {
			$("#chatarea").append(event.data + "<br>");
			console.log("�����κ��� �޽��� ���� ����");
		};
	})
	</script>
</body>
</html>