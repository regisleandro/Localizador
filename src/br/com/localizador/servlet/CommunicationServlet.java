package br.com.localizador.servlet;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@SuppressWarnings("deprecation")
@WebServlet("/websocket")
public class CommunicationServlet extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
	private static final List<ConnectionWS> connections = new ArrayList<ConnectionWS>();
	
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,HttpServletRequest request) {
		String username = request.getParameter("username");
		return new ConnectionWS(username);
	}

	public static final List<ConnectionWS> getConnections() {
		return connections;
	}

	public static final void broadcast(String message, String username) {
		
		for (ConnectionWS connection :  CommunicationServlet.getConnections()) {
			try {
				connection.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
			} catch (IOException ignore){
			// Ignore
			}
		}

	}
}
