package websocket.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketListener {

	
	@EventListener
	private void handleSessionConnecton(SessionConnectEvent event) {
		
	}
	
	@EventListener
	private void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
		
	}
}
