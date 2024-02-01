package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

import java.io.IOException;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}

	/**
	 * Etablerer en forbindelse til serveren ved hjelp av MessagingClient.
	 */
	public void connect() {

			connection = msgclient.connect();

	}

	/**
	 * Dersom det er tilkobling så lukkes forbindelsen.
	 */
	public void disconnect() {
		if (connection != null){
			connection.close();
		}

	}

	/**
	 *
	 * @param rpcid
	 * @param param
	 * Connection.send sender meldingen.
	 * reply mottar meldingen som er sendt.
	 * @return returnval
	 */
	public byte[] call(byte rpcid, byte[] param) {
		
		byte[] returnval = null;
		byte[] rpcmsg = RPCUtils.encapsulate(rpcid, param);
		connection.send(new Message(rpcmsg));

		Message reply = connection.receive();
		returnval = RPCUtils.decapsulate(reply.getData());

		return returnval;
		
	}

}
