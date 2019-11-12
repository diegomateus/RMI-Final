package interfazRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import controladorServidor.Transaccion;
import modelo.Oferta;

public interface InterfazServidor extends Remote {

	public int puedeConsumarCita( Transaccion t) throws RemoteException;
	public Oferta consumarCita( Transaccion t) throws RemoteException;
	public boolean abortar( Transaccion t) throws RemoteException;
	public void cargarOfertas(Transaccion t) throws RemoteException;
	
	
}
