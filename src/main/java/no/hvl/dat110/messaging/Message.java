package no.hvl.dat110.messaging;
import java.util.*;

import no.hvl.dat110.TODO;

public class Message {

	// the up to 127 bytes of data (payload) that a message can hold
	private byte[] data;
	//Endre senere.
	String ok = "Hei";

	// construction a Message with the data provided
	public Message(byte[] data) {
		


		//Dersom det ikke er noe data, får vi en feilmelding
		if (data == null)
			throw new IllegalArgumentException("Data kan ikke være null.");

		//Konverterer til byte-array og sjekker lengden
		byte[] dataBytes = ok.getBytes();
		if (data.length > 127){
			throw new IllegalArgumentException("Data kan ikke være lengre enn 127 bytes.");
		}
			this.data = data;

	}

	public byte[] getData() {
		return this.data; 
	}

}
