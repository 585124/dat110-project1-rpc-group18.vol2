package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * @param message
	 * Kaller på encapsulate som pakker inn meldingen, krypteter (Protocol layers).
	 * Flush tvinger meldingen til å bli sendt og ikke bli liggende i buffer.
	 */
	public void send(Message message){

	byte[] data = MessageUtils.encapsulate(message);

		try{

			outStream.write(data); //Denne skriver meldingen til outstream
			outStream.flush(); //Tvinger meldingen til å bli sendt ut av buffer
		} catch (IOException e){
			System.out.println("Outputstream Failed: " + e.getMessage());
			//e.printStackTrace();
		}

	}

	public Message receive() {
/**
 * Tar imot meldingen og leser opp til 128 bytes.
 * Dersom meldingen er større får vi feilmelding.
 * Pakker ut meldingen (decapsulate).
 * @return message
 */
		Message message = null;
		byte[] data = null;

		// read a segment from the input stream and decapsulate data into a Message

		data = new byte[MessageUtils.SEGMENTSIZE];

		try{
			int read = inStream.read(data, 0, MessageUtils.SEGMENTSIZE);
			//Hente meldinger fra instream, begynner å lese av fra plass 0, til og med 128(segmetsize)
			message = MessageUtils.decapsulate(data); //dekrypterer eller pakker ut data

		} catch (IOException e) {
			System.out.println("Instream Failed: " + e.getMessage());
			//dersom størrelsen er større en 128
		}

		return message;
		
	}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}