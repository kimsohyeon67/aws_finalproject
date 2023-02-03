package websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class ChatWebSocketConfig implements WebSocketConfigurer {

	// /ws 요청 시 ChatWebSocketHandler에 매핑
	private final WebSocketHandler handler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(handler, "/ws").setAllowedOrigins("*");
	}

	public ChatWebSocketConfig(WebSocketHandler handler) {
		super();
		this.handler = handler;
	}
}
