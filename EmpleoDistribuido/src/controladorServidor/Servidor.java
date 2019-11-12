package controladorServidor;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;

import interfazRMI.InterfazServidor;
import modelo.Oferta;
import modelo.Candidato;
import modelo.Empresa;
import modelo.Estudio;
import modelo.Experiencia;

public class Servidor extends UnicastRemoteObject implements InterfazServidor {

	protected Servidor() throws RemoteException {
		super();
		this.ofertas = new ArrayList<>();
		
		// TODO Auto-generated constructor stub
	}

	private ArrayList<Oferta> ofertas;

	public static void main(String args[]) {
		// Create and install a security manager
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			System.out.println("Entra");
			Servidor servidor = new Servidor();
			
			System.out.println("Entra --> 1");
			// Bind this object instance to the name "HelloServer"
			Naming.rebind("//127.0.0.1:1500/Server", servidor);
			
			System.out.println("Entra --> 2");

			System.out.println("Server bound in registry");
		} catch (Exception e) {
			System.out.println("Server err: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public int puedeConsumarCita(Transaccion t) {
		// TODO Auto-generated method stub
		
		int puedeConsumar = 0;
		int puntaje = 0;
		Candidato c = (Candidato) t.getObjeto();
		
		try {

			for (Oferta oferta : ofertas) {
				
				//Verificar que sea valida la lectura de la oferta
				if (t.getTimeStamp().isAfter(oferta.getTimeStampEscritura())) {

					if (oferta.getDseleccionada().getTimeStampEscritura()
							.compareTo(oferta.getTimeStampEscritura()) <= 0) {

						oferta.setTimeStampLectura(t.getTimeStamp());
						
						//Calcular puntaje
						puntaje = evaluarCandidato(oferta, c);

					} else {
						
						synchronized(oferta.getDseleccionada()){
								oferta.getDseleccionada().wait();
						}
					}
				} else {
					// Abortar transaccion
					System.out.println("Llego al server -1.2: " + t.getTimeStamp());
					
					return -1;
				}
				
				System.out.println( puntaje );
				//Si el puntaje es mayor o igual a 70 verificar que la escritura sea valida
				if( puntaje >= 70 && oferta.getCitaAsignada().size() < 3 ){
					
					if( t.getTimeStamp().compareTo( oferta.getTimeStampLectura()) >= 0 
							&& t.getTimeStamp().isAfter( oferta.getTimeStampEscritura()) ){
						Oferta tentativa = new Oferta( oferta.getCargo(), oferta.getExperiencia(), oferta.getSalarioOfrecido(), 
								oferta.getSectorEmpresa(), oferta.getNivelEstudio(), oferta.getTimeStampLectura(), t.getTimeStamp());
						
						oferta.setDseleccionada( tentativa );
						
						tentativa.addCitaAsignada( c.getDocumento() );
						oferta.addTentativa(tentativa);
						System.out.println("Llego al server 1: " + t.getTimeStamp());
						return 1;
					}else{
						//Abortar transaccion
						System.out.println("Llego al server -1.1: " + t.getTimeStamp());
						
						return -1;
					}
					
				}else{
					//No se ejecuto ninguna instruccion
					System.out.println("Llego al server 0: " + t.getTimeStamp());
					
					return 0;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Llego al server 0.0: " + t.getTimeStamp());
		
		return puedeConsumar;
	}

	@Override
	public Oferta consumarCita(Transaccion t) {
		// TODO Auto-generated method stub
		
		Oferta nuevoValor = null;
		for( Oferta oferta : ofertas){
			
			for( Oferta tentativa : oferta.getTentativas()){
				if( tentativa.getTimeStampEscritura().equals( t.getTimeStamp() )){
					oferta.setCitaAsignada( tentativa.getCitaAsignada() );
					oferta.setTimeStampEscritura( tentativa.getTimeStampEscritura() );
					nuevoValor = oferta;
					synchronized( tentativa ){
						tentativa.notify();
					}
				}
			}
		}
		return nuevoValor;
	}

	@Override
	public boolean abortar(Transaccion t) {
		// TODO Auto-generated method stub
		return false;
	}

	public int evaluarCandidato(Oferta oferta, Candidato c) {

		//Retardo forzado
		try {
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int puntaje = 0;
		boolean desempenioCargo = false;
		int experienciaTotal = 0;
		int experienciaSector = 0;
		
		//Verificar que desempenio el cargo
		for( Experiencia exp : c.getHistorial() ){
			
			if( exp.getCargo().equals( oferta.getCargo() )){
				desempenioCargo = true;
				experienciaTotal += exp.getDuracion();
				if( exp.getSector().equals( oferta.getSectorEmpresa())){
					experienciaSector += exp.getDuracion();
				}
			}
		}
		
		if( desempenioCargo){
			//Verificar experiencia = +20
			if( experienciaTotal >= oferta.getExperiencia())
				puntaje+=20;
			//Verificar nivel de estudio +20
			if( c.getNivelEstudio().compareTo(oferta.getNivelEstudio() ) >= 0 )
				puntaje+=20;
			//Verificar aspiracion salarial +20
			if( c.getAspiracionSalarial() <= oferta.getSalarioOfrecido())
				puntaje+=20;
			//Bonus experiencia
			if( experienciaTotal >= oferta.getExperiencia())
				puntaje+=12;
			//Bonus Salario
			if( c.getAspiracionSalarial() < oferta.getSalarioOfrecido() )
				puntaje+=13;
			//Bonus Sector
			if( experienciaSector >= oferta.getExperiencia()/2)
				puntaje+=15;
		}
		
		return puntaje;
	}
	
	@Override
	public void cargarOfertas(Transaccion t) {
		Empresa e = (Empresa) t.getObjeto();
		
		for (Oferta o: e.getOfertas()) {
			this.ofertas.add(o);
		}
	}

}
