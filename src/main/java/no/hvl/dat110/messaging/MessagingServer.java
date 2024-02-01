package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessagingServer {

	// server-side socket for accepting incoming TCP connections
	private ServerSocket welcomeSocket;

	public MessagingServer(int port) {

		try {

			this.welcomeSocket = new ServerSocket(port);

		} catch (IOException ex) {

			System.out.println("Messaging server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// accept an incoming connection from a client
	public MessageConnection accept() {

		/**
		 * Aksepterer en forbindelse til klienten.
		 * Så bruker vi messageConnection for å sende og motta meldinger fra klienten.
		 * Har med et unntak dersom det oppstår problemer med input/output data.
		 * @return connection
		 */
		MessageConnection connection = null;

		// accept TCP connection on welcome socket and create messaging connection to be returned
		try{

			Socket clientSocket = welcomeSocket.accept();

			connection = new MessageConnection(clientSocket);
		} catch (IOException ex){
			System.out.println("Messageing Server: " + ex.getMessage());
		}
		return connection;

	}

	public void stop() {

		if (welcomeSocket != null) {

			try {
				welcomeSocket.close();
			} catch (IOException ex) {

				System.out.println("Messaging server: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}
