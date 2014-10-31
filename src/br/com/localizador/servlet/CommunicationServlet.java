package br.com.localizador.servlet;
/***
 * Implementação websocket do exemplo
 * http://www.devmedia.com.br/introducao-a-websockets-com-tomcat-7/26932
 * comunicação entre os usuários
 * 
 */
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@SuppressWarnings("deprecation")
@WebServlet("/websocket")
public class CommunicationServlet extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
	private static final List<ConnectionWS> connections = new ArrayList<ConnectionWS>();

	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
			HttpServletRequest request) {
		String username = request.getParameter("username");
		return new ConnectionWS(username);
	}

	public static final List<ConnectionWS> getConnections() {
		return connections;
	}

	public static final void broadcast(String message) {
		for (ConnectionWS con : CommunicationServlet.getConnections()) {
			try {
				con.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				System.out.println("Enviando mensagem de texto (" + message
						+ ")");
			} catch (IOException ioe) {
				System.out.println("Aconteceu um erro");
			}
		}
	}
}
