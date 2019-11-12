/**
 * 
 */
package controladorEmpleador;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import interfazRMI.InterfazEmpleador;
import modelo.Empresa;
import modelo.Oferta;
import vista.VistaEmpleador;

/**
 * @author migue
 *
 */
public class ThreadEmpleador implements Runnable {
	private InterfazEmpleador rmi;
	private VistaEmpleador gui;
	private Empresa empresa;
	/**
	 * @param gui
	 * @param empresa
	 */
	public ThreadEmpleador(VistaEmpleador gui, Empresa empresa) {
		super();
		try {
			this.rmi = (InterfazEmpleador) Naming.lookup("//127.0.0.1:1500/EmpleadorServer");
			this.gui = gui;
			this.empresa = empresa;
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		String trans = " ";
		System.out.println(empresa.getNombre());
		try {
			trans = rmi.iniciarTransaccion(empresa.getNombre());
			gui.ActualizarLog( "Creación", empresa, null);
			rmi.publicarOferta(empresa);
			for (Oferta o: empresa.getOfertas()) {
				gui.ActualizarLog( "Publicación oferta", empresa, o);
			}
			rmi.finalizarTransaccion(trans);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}						
	}
}
