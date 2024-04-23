package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		//Krypterer meldingen for å klargjøre til sending (pakker den inn)
		/**
		 * @param message
		 * Oppretter en tabell med 128 plasser.
		 * Kopierer meldingen over til den nye tabellen.
		 * @return segment.
		 */
		byte[] segment = new byte[SEGMENTSIZE];
		byte[] data = message.getData();

		segment[0] = (byte) data.length;
		for(int i = 0; i < data.length; i++){
			segment[i + 1] = data[i];
		}
		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {
	//Pakker ut meldingen ved mottak, dekryptere
		/**
		 * @param segment
		 * Pakker ut meldingen, leser av tabellen og kopierer hver
		 * plass i segment-tabellen over til data-tabellen.
		 * return message
		 */
		Message message = null;
		

		// decapsulate segment and put received payload data into a message
		int len = segment[0];
		byte[] data = new byte[len];

		for (int i = 0; i < len; i++){
			data[i] = segment[i + 1];
		}

		message = new Message(data);
		return message;
		
	}
	
}
