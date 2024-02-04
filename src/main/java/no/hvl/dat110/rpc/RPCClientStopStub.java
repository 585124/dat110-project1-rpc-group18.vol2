package no.hvl.dat110.rpc;

/**
 * Denne klassen tillater klienten å kalle den
 * innebygde stoppmetoden på RPC-serveren ved å
 * sende en RPC-forespørsel gjennom RPC-klienten
 * og håndtere eventuelle svar som returneres
 */
public class RPCClientStopStub extends RPCLocalStub {

	public RPCClientStopStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	// client-side implementation of the built-in server stop RPC method
	public void stop () {
		
		byte[] request = RPCUtils.marshallVoid();
		
		byte[] response = rpcclient.call(RPCCommon.RPIDSTOP,request);
		
		RPCUtils.unmarshallVoid(response);
	
	}
}
