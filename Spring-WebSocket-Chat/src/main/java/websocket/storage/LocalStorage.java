package websocket.storage;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;


public class LocalStorage {

    private Map<String, String> activeUsers = new ConcurrentHashMap<>();
	
	public static LocalStorage getInstance() {
		return SingletonLocalStorage.INSTANCE;
	}
	
	public void putActiveUser(String sessionID, String username) {
		activeUsers.putIfAbsent(sessionID, username);
	}
	
	public Collection<String> getActiveUsers() {
		return activeUsers.values();
	}
	
	public String getActiveUser(String username) {
		String sessionID = "";
		
		for(Entry<String, String> entry : activeUsers.entrySet()) {
			 if(entry.getValue().equals(username)) {
				 sessionID = entry.getKey();
			 }
		}
		
		return sessionID;
		
	}
	
	public String removeActiveUser(String sessionID) {
		return activeUsers.remove(sessionID);
	}

	private static class SingletonLocalStorage {
		private static final LocalStorage INSTANCE = new LocalStorage();
	}
}
