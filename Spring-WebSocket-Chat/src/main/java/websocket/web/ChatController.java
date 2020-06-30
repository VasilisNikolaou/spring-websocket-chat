package websocket.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import websocket.domain.ChatMessage;
import websocket.storage.LocalStorage;

@Controller
public class ChatController {
	
	@Autowired
	private SimpMessagingTemplate template;

	@SubscribeMapping("/participants")
	public Collection<String> getParticipants() {
		return LocalStorage.getInstance().getActiveUsers();
	}
	
	@MessageMapping("/register")
	@SendTo("/topic/register")
	public ChatMessage chatMessageRegister(@Payload ChatMessage message) {
		ChatMessage cMessage = new ChatMessage();
		cMessage.setSender(message.getSender());
		
		return cMessage;
	}
	
	@MessageMapping("/chat.message")
	@SendTo("/topic/public")
	public ChatMessage broadcastPublicMessage(@Payload ChatMessage message) {
		return message;
	}
	
	@MessageMapping("/chat.private.{sendTo}")
	public void broadcastPrivateMessage(@DestinationVariable("sendTo") String sendTo, 
			@Payload ChatMessage message) {
		
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		
		String sessionID = LocalStorage.getInstance().getActiveUser(sendTo);
		
        headerAccessor.setSessionId(sessionID);
        headerAccessor.setLeaveMutable(true);
        
        template.convertAndSendToUser(sessionID, "/queue/private", message, 
        		headerAccessor.getMessageHeaders());
	}
}
