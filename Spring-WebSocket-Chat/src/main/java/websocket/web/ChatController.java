package websocket.web;

import java.util.Collection;

import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import websocket.storage.LocalStorage;

@Controller
public class ChatController {

	@SubscribeMapping("/participants")
	public Collection<String> getParticipants() {
		return LocalStorage.getInstance().getActiveUsers();
	}
}
