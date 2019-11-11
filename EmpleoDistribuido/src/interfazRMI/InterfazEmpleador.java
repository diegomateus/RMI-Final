package interfazRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import modelo.Empresa;

public interface InterfazEmpleador extends Remote {

	String asignarCitas( Empresa e) throws RemoteException;
	
}
