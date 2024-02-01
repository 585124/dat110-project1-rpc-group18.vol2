package no.hvl.dat110.messaging;


import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessagingClient {

	// name/IP address of the messaging server
	private String server;

	// server port on which the messaging server is listening
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	// setup of a messaging connection to a messaging server
	public MessageConnection connect () {

/**
 * Oppretter en socket forbindelse for å sikre at serveren og porten kan kommunisere
 * Dersom det oppstår en feil får vi et unntak, og en feilmelding skrives ut.
 * @return connection
 */
		// client-side socket for underlying TCP connection to messaging server
		Socket clientSocket = null;

		MessageConnection connection = null;
		

		// connect to messaging server using a TCP socket
		// create and return a corresponding messaging connection
		try{
			clientSocket = new Socket(server, port);
			connection = new MessageConnection(clientSocket);
		} catch (Exception e){
			System.out.println("MessagingClient: " + e.getMessage());
			//e.printStackTrace();
		}

		return connection;
	}
}
