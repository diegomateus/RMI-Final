package interfazRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import modelo.Candidato;
import modelo.Oferta;

public interface InterfazCandidato extends Remote{
	
	String abrirTransaccion( String usuario ) throws RemoteException; 
	
	Oferta obtenerOferta( Candidato cliente ) throws RemoteException;
	
	boolean cerrarTransaccion( String trans ) throws RemoteException;
	
}
