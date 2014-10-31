package br.com.localizador.servlet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

@SuppressWarnings("deprecation")
public final class ConnectionWS extends MessageInbound {
	private final String username;

	public ConnectionWS(String username) {
		this.username = username;
	}

	@Override
	protected void onOpen(WsOutbound outbound) {
		// Adiciona essa nova conexão a lista de conexões
		CommunicationServlet.getConnections().add(this);
		String message = String.format("\"%s\" se conectou.", username);
		CommunicationServlet.broadcast(message);
	}

	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		throw new RuntimeException("Metodo não aceito");
	}

	@Override
	protected void onTextMessage(CharBuffer msg) throws IOException {
		String message = String.format("\"%s\": %s", username, msg.toString());
		CommunicationServlet.broadcast(message);
	}
}
