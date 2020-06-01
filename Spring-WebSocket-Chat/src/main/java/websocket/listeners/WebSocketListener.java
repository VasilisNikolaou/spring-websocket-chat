package websocket.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import websocket.storage.LocalStorage;

@Component
public class WebSocketListener {

	
	@EventListener
	private void handleSessionConnecton(SessionConnectEvent event) {
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
		
		String sessionID = headerAccessor.getSessionId();
		String username = headerAccessor.getNativeHeader("username").get(0);
		
		LocalStorage.getInstance().putActiveUser(sessionID, username);
	}
	
	@EventListener
	private void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
		
	}
}