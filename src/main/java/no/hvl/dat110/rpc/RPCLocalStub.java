package no.hvl.dat110.rpc;

// RPC client-side (local) stubs must extend this class

/**
 * Denne klassen fungerer som en baseklasse for lokale
 * stubber på klient-siden for RPC. Denne klassen er
 * ment for å bli utvidet av klient-sien for å gi felles
 * funksjonalitet og tilgang til RPC-klienten.
 * Klassen har en konstruktør som tar inn en felles
 *  * referanse til en RPC-klient. Stub-klasser som utvider
 *  * denne basisklassen vil dermed kunne bruke den eksisterende
 *  * RPC-klienten til å sende RPC-forespørsler til den
 *  * eksterne/remote serveren
 */
public abstract class RPCLocalStub {

	protected RPCClient rpcclient;
	
	public RPCLocalStub(RPCClient rpcclient) {
		this.rpcclient = rpcclient;
	}
	
}


