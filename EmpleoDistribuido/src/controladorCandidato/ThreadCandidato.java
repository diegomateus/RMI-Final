package controladorCandidato;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import modelo.Candidato;
import modelo.Estudio;
import modelo.Experiencia;
import modelo.Oferta;
import vista.Informacion;
import interfazRMI.*;

public class ThreadCandidato implements Runnable {

	private InterfazCandidato rmi;
	private Informacion GUI;
	private Candidato candidato;
	
	public ThreadCandidato(Informacion GUI, Candidato candidato) { 
		// TODO Auto-generated constructor stub
		try {
			
			this.rmi = (InterfazCandidato) Naming.lookup("//127.0.0.1:1500/CandidatoServer");
			this.GUI = GUI;
			this.candidato = candidato;
			
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
	
	@Override
	public void run() {
		
		//Enviar solicitudes automaticas
		String trans;
		Oferta oferta;
		try {
			System.out.println(candidato);
			trans = rmi.abrirTransaccion( candidato.getNombre() );
			
			GUI.ActualizarLog( "Creacion", candidato, null);
			oferta = rmi.obtenerOferta( candidato );
			GUI.ActualizarLog( "Asignacion Cita", candidato, oferta);
			rmi.cerrarTransaccion(trans);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
						
		
	}

}
