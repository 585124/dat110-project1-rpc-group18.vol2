package no.hvl.dat110.rpc;

import java.util.HashMap;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.MessageConnection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private MessageConnection connection;
	
	// hashmap to register RPC methods which are required to extend RPCRemoteImpl
	// the key in the hashmap is the RPC identifier of the method
	private HashMap<Byte,RPCRemoteImpl> services;
	
	public RPCServer(int port) {
		
		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Byte,RPCRemoteImpl>();
		
	}
	
	public void run() {
		
		// the stop RPC method is built into the server
		RPCRemoteImpl rpcstop = new RPCServerStopImpl(RPCCommon.RPIDSTOP,this);
		
		System.out.println("RPC SERVER RUN - Services: " + services.size());
			
		connection = msgserver.accept(); 
		
		System.out.println("RPC SERVER ACCEPTED");
		
		boolean stop = false;
		
		while (!stop) {

			byte rpcid = 0;
			Message requestmsg, replymsg;
			try{
			   //Mottar medling
			    requestmsg = connection.receive();
			   byte[] request = requestmsg.getData();

			   System.out.println(request.length);
			   //Dekapsulere og indentifisere RPCmetoden
			   rpcid = request[0];
			   byte[] param = RPCUtils.decapsulate(request);

			   //Finn den tilsvarende metoden
			   RPCRemoteImpl method = services.get(rpcid);
			   if (method == null){
				   throw new UnsupportedOperationException("Metode ikke funnet.");
			   }

			   //Kjør metoden og få svar
				byte[] response = method.invoke(param);

			   //Send tilbake et svar
				replymsg = new Message(RPCUtils.encapsulate(rpcid, response));
				connection.send(replymsg);

				//Sjekk om det er stop-metoden
				if(rpcid == RPCCommon.RPIDSTOP){
					stop = true;
				}

		   }catch(Exception e){
			   e.printStackTrace();
		   }

		}
	
	}
	
	// used by server side method implementations to register themselves in the RPC server
	public void register(byte rpcid, RPCRemoteImpl impl) {
		services.put(rpcid, impl);
	}
	
	public void stop() {

		if (connection != null) {
			connection.close();
		} else {
			System.out.println("RPCServer.stop - connection was null");
		}
		
		if (msgserver != null) {
			msgserver.stop();
		} else {
			System.out.println("RPCServer.stop - msgserver was null");
		}
		
	}
}
