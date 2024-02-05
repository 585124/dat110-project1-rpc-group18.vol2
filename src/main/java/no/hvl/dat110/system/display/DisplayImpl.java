package no.hvl.dat110.system.display;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCRemoteImpl;
import no.hvl.dat110.rpc.RPCUtils;
import no.hvl.dat110.rpc.RPCServer;

public class DisplayImpl extends RPCRemoteImpl {

	public DisplayImpl(byte rpcid, RPCServer rpcserver) {
		super(rpcid,rpcserver);
	}

	public void write(String message) {
		System.out.println("DISPLAY:" + message);
	}
	
	public byte[] invoke(byte[] param) {
		
		byte[] reply = null;
		

		// implement unmarshalling, call, and marshall for write RPC method
		// look at how this is done in the SensorImpl class for the read method

		// gjør bit om til en lesbar tekst.
		//kaller write metoden og denne viser mld i konsollen
		String resp = RPCUtils.unmarshallString(param);
		write(resp);

		reply = RPCUtils.marshallVoid();
		//oppretter et tomt byte-array som returverdi, dette indikerer til
		//klienten at metoden ble utført uten feil, men uten spesifikke returdata
		return reply;
	}
}
