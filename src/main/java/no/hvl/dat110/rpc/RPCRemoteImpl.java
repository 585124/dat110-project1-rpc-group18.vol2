package no.hvl.dat110.rpc;

// RPC server-side method implementations must extend this class

public abstract class RPCRemoteImpl {

	/**
	 *
	 * @param rpcid
	 * @param rpcserver
	 * Denne metoden setter opp et rammeverk for RPC-
	 * serverimplementasjon ved å registrere seg hos
	 * RPC serveren og definere en abstrakt invoke metode
	 * 'invoke' som skal kalles når RPC-forespørselen blir
	 * mottatt /når rikig ID blir registrert.
	 */
	public RPCRemoteImpl(byte rpcid, RPCServer rpcserver) {

		rpcserver.register(rpcid, this);
	}

	// method that will be invoked by the server
	// params
	public abstract byte[] invoke(byte[] params);
	
}
