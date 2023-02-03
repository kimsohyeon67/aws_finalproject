package websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ChatWebSocketHandler implements WebSocketHandler{

	//웹소켓 여러 클라이언트 공유
	public static List<WebSocketSession> list = new ArrayList();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//클라이언트 요청시 즉각 호출
		list.add(session);
		
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		//클라이언트로부터 수신시 즉각 호출
		//nickname:xxxxx
		String msg = (String)message.getPayload();
		
		for(WebSocketSession s : list)
		{
			/*
			 * if(s == session) { continue; }
			 */
			WebSocketMessage<String> sendmsg = new TextMessage(msg);
			s.sendMessage(sendmsg);
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		//
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		//클라이언트 연결 해제시 즉각 호출
		list.remove(session);
		
	}

	@Override
	public boolean supportsPartialMessages() {
		//클라이언트 요청시 즉각 호출
		return false;
	}

	
}
