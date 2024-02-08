package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		/**
		 * @param rpcid
		 * @param payload
		 * Denne metoden innkapsler RPC-meldinger.
		 * @return rpcmsg
		 */
		//int len = payload != null ? payload.length : 0;

		byte[] rpcmsg = new byte[payload.length + 1];
		rpcmsg[0] = rpcid;
        System.arraycopy(payload, 0, rpcmsg, 1, payload.length);
		// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format
		return rpcmsg;
	}
	/**
	 * @param rpcmsg
	 * Denne metoden avkapsler RPC meldinger.
	 * @return payload
	 */
	public static byte[] decapsulate(byte[] rpcmsg) {

		/**
		 * @param rpcmsg
		 * Denne metoden avkapsler RPC meldinger.
		 * @return payload
		 */
		byte[] payload = new byte[rpcmsg.length - 1];
		for (int i = 0; i < payload.length; i++){
			payload[i] = rpcmsg[i + 1];
		}

		// Decapsulate the rpcid and payload in a byte array according to the RPC message syntax
		return payload;
		
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		/**
		 * @param str
		 * Konverterer en String til en byte.
		 * @return encoded
		 */
		byte[] encoded = str.getBytes();
		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		/**
		 * @param data
		 * Konverterer en byte-array til en String.
		 * @return decoded
		 */
		String decoded = new String(data, StandardCharsets.UTF_8);
		return decoded;
	}
	
	public static byte[] marshallVoid() {

		Studentassisten: return new byte[0];

		
	}
	
	public static void unmarshallVoid(byte[] data) {

		
	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	/**
	 * @param x
	 * Oppretter en buffer ved hjelp av Bytebuffer-klassen med
	 * kapasitet på 4 bytes.
	 * buffer.putInt(x) putter verdien x inn i bufferen.
	 * putInt konverterer heltallet til et byte-array i henhold til
	 * standard byte-rekkefølge (big-endian som standard).
	 * @return buffer.array().
	 */
	public static byte[] marshallInteger(int x) {
		byte[] encoded = null;


		//ByteBuffer buffer = ByteBuffer.allocate(4);
		//buffer.putInt(x);
		//return buffer.array();
		encoded = ByteBuffer.allocate(4).putInt(x).array();

		return encoded;
	}

	/**
	 * Vi oppretter en ny buffer. Så bruker vi metoden wrap som settes
	 * rundt 'data' tabellen.
	 * @param data
	 * @return buffer.getInt()
	 * På grunn av wrap metoden kan vi hente verdiene
	 * direkte fra 'data' tabellen.
	 */
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {

		ByteBuffer buffer = ByteBuffer.wrap(data);
		return buffer.getInt();

	}
}
