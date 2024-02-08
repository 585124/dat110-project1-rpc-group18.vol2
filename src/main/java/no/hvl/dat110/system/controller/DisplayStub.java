package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;


public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {
		//RPC-ID for write-metoden
		byte rpcid = (byte) Common.WRITE_RPCID;

		//Marshalling av strengen til et byte-array
		byte[] request = RPCUtils.marshallString(message);

		//Forbereder RPC mld ved å legge til RPC -ID foran marshalled data
		//byte[] rpcmsg = RPCUtils.encapsulate(rpcid, request);

		//utfører RPC-kallet ved å sende RPC mld til serveren
		byte[] reply = rpcclient.call(rpcid, request);

		RPCUtils.unmarshallVoid(reply);


		
		// implement marshalling, call and unmarshalling for write RPC method
		
//		if ( reply != null && reply.length > 0) {
//			System.out.println("Bekreftelse mottatt fra serveren");
//		} else{
//			System.out.println("Ingen bekreftelse mottatt fra serveren");
//		}
		// Denne if setningen sjekker om mld er tom
	}
}
