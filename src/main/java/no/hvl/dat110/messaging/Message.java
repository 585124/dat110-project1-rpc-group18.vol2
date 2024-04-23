package no.hvl.dat110.messaging;
import java.util.*;

import no.hvl.dat110.TODO;

public class Message {

	//Kontruktøren
	private byte[] data;

	//Test melding
	String ok = "Hei";


	public Message(byte[] data) {

		//Dersom det ikke er noe data (null), får vi en feilmelding
		if (data == null)
			throw new IllegalArgumentException("Data kan ikke være null.");

		//Konverterer til byte-array og sjekker lengden (127 bytes, som oppgitt i oppg.)
		byte[] dataBytes = ok.getBytes();
		if (data.length > 127){
			throw new IllegalArgumentException("Data kan ikke være lengre enn 127 bytes.");
		}
			this.data = data;

	}

	public byte[] getData() {
		return this.data; 
	}
	//Denne metoden brukes kun til å hente ut data tabellen
}
