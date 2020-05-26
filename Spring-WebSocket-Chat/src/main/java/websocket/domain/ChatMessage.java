package websocket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

	private String sender;
	private String content;
	private Type type;
	
	public enum Type {
		PUBLIC, PRIVATE
	}
}
