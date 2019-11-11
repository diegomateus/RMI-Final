package controladorServidor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import interfazRMI.InterfazCandidato;
import interfazRMI.InterfazEmpleador;
import interfazRMI.InterfazServidor;
import modelo.Candidato;
import modelo.Empresa;
import modelo.Oferta;

public class Coordinador extends UnicastRemoteObject implements InterfazCandidato, InterfazEmpleador{

	private ArrayList< InterfazServidor >Participantes;
	private ArrayList< Transaccion >transacciones;
	
	public Coordinador() throws RemoteException{
		super();
		try {
			this.Participantes = new ArrayList<>();
			this.transacciones = new ArrayList<>();
			
			Participantes.add( (InterfazServidor) Naming.lookup("//127.0.0.1:1500/Server") );
			//Participantes.add( (InterfazServidor) Naming.lookup("127.0.0.1:1500/Server2") );
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		// Create and install a security manager
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			
			Coordinador coordinador = new Coordinador();
			
			// Bind this object instance to the name "HelloServer"
			Naming.rebind("//127.0.0.1:1500/CandidatoServer", coordinador);

			System.out.println("CandidatoServer bound in registry");
		} catch (Exception e) {
			System.out.println("HelloImpl err: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public String abrirTransaccion(String usuario) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(usuario);
		return usuario;
	}

	@Override
	public Oferta obtenerOferta(Candidato candidato ) throws RemoteException {
		// TODO Auto-generated method stub
		
		System.out.println("Llego al server ");
		Oferta oferta = new Oferta();
		oferta.setCargo("NO LLEGO");
		Transaccion t = new Transaccion( candidato );
		synchronized ( this.transacciones ) {
			this.transacciones.add( t );
			this.transacciones.sort(null);
		}
		
		boolean consumar = false;
		while( !consumar ){
			//Verificar si algun participante puede consumar
			for( InterfazServidor rmi : this.Participantes ){
				
				if( rmi.puedeConsumarCita(t) > 0 ){
					synchronized ( this.transacciones ) {
						//Verificar que la transaccion que desea hacer commit sea la siguiente en la lista 
						if( t.getTimeStamp().equals( this.transacciones.get(0).getTimeStamp() )){
							oferta = rmi.consumarCita( t );
							this.transacciones.remove(0);
							consumar = true;
							this.transacciones.notifyAll();
						}else{
							try {
								this.transacciones.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				}
				
			}
			//System.out.println("Consumar:" + consumar + "Oferta:" + oferta);
			
		}
		
		return oferta;
	}

	@Override
	public boolean cerrarTransaccion(String trans) throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public String asignarCitas(Empresa e) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
