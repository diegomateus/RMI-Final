package interfazRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import modelo.Empresa;
import modelo.Oferta;

public interface InterfazEmpleador extends Remote {

	String asignarCitas( Empresa e) throws RemoteException;
	
	String iniciarTransaccion(String empresa) throws RemoteException;
	
	boolean finalizarTransaccion (String t) throws RemoteException;
	
	void publicarOferta (Empresa e) throws RemoteException;
	
}
