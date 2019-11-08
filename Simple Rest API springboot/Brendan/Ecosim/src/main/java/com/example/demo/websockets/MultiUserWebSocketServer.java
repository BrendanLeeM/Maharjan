package com.example.demo.websockets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Worlds;
import com.example.demo.worldRepository;
/**
 * Class to handle websocket connections
 * @author Vamsi
 * Editied by Brendan to fit our application
 *
 */
@ServerEndpoint(value = "/MultiUserwebsocket/{username}", configurator = CustomConfigurator.class)
@Component
public class MultiUserWebSocketServer {
	
    private static Map<Session, String> sessionUsernameMap = new HashMap<>();
    private static Map<String, Session> usernameSessionMap = new HashMap<>();
    
    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    /**
     * Class which sends all worlds to any user who connects to the socket
     * @param session
     * @param username
     * @throws IOException
     */
    @OnOpen
    public void onOpen(
    	      Session session, 
    	      @PathParam("username") String username) throws IOException 
    {
    	
        logger.info("Entered into Open");
        
        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);
       
        String message = username + "Connected";
      
        	
		
    }
 /**
  * Handle client message
  * @param session
  * @param message
  * @throws IOException
  */
    @OnMessage
    public void onMessage(Session session, String message) throws IOException 
    {
       
    	broadcast(message);
    	
    }
 /**
  * Actions upon websocket close
  * @param session
  * @throws IOException
  */
    @OnClose
    public void onClose(Session session) throws IOException
    {
    	logger.info("Entered into Close");
    	
    	String username = sessionUsernameMap.get(session);
    	sessionUsernameMap.remove(session);
    	usernameSessionMap.remove(username);
        
    	String message= username + " disconnected";
        broadcast(message);
    }
 /**
  * Handle websocket errors
  * @param session
  * @param throwable
  */
    @OnError
    public void onError(Session session, Throwable throwable) 
    {
        // Do error handling here
    	logger.info("Entered into Error");
    }
    
	private void sendMessageToPArticularUser(String username, String message) 
    {	
    	try {
    		usernameSessionMap.get(username).getBasicRemote().sendText(message);
        } catch (IOException e) {
        	logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
        }
    }
    /**
     * Broadcast a message 
     * @param message
     * @throws IOException
     */
    public static void broadcast(String message) 
    	      throws IOException 
    {	  
    	sessionUsernameMap.forEach((session, username) -> {
    		synchronized (session) {
	            try {
	                session.getBasicRemote().sendText(message);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
}

