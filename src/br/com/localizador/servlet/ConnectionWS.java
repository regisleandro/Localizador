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
		// Adiciona essa nova conexao a lista de conxoes
		CommunicationServlet.getConnections().add(this);
		String message = String.format("\"%s\" se conectou.", username);
		CommunicationServlet.broadcast(message, username);
	}

	@Override
	protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
		throw new RuntimeException("Metodo nao aceito");
	}

	@Override
	protected void onTextMessage(CharBuffer msg) throws IOException {
		String message = String.format("\"%s\": %s", username, msg.toString());
		CommunicationServlet.broadcast(message, username);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConnectionWS other = (ConnectionWS) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
