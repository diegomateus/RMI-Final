package controladorCandidato;

import modelo.Candidato;
import modelo.Estudio;
import modelo.Experiencia;
import vista.Informacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.rmi.Naming; 
import java.rmi.RemoteException;
import java.util.ArrayList; 

public class Empleado {
	
	
	public static void main( String args[] ){
		try{
			
			Informacion GUI = new Informacion();
			GUI.setVisible(true);
			
			while( true ){
			//int i = 0;
				for( Candidato c: leerCandidatos()){
						Thread.sleep( c.getTimeStamp()*1000 );
						ThreadCandidato tcandidato = new ThreadCandidato(GUI, c);
						Thread t = new Thread( tcandidato );
						t.start();
					
				}
			}
			
		}catch( Exception e)
		{
			System.out.println("HelloImpl err: " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	public static ArrayList<Candidato> leerCandidatos(){
		

		String cadena;
		Candidato c = null;
		ArrayList<Candidato> candidatos = new ArrayList<>();
		String datos[];
		
		try {
			FileReader f = new FileReader( Paths.get("infoCandidatos").toString() );
			BufferedReader b = new BufferedReader(f);
	        while((cadena = b.readLine())!=null) {
	            if( cadena.equals("Candidato") ){
	            	
	            	cadena = b.readLine();
	            	datos = cadena.split(";");
	            	/* Formato Candidato
	            	 Nombre;Documento;aspiracionSalarial;NivelEstudio;timeStamp*/
	            	double salario = Double.parseDouble( datos[2] );
	            	Estudio nivelEstudio = Estudio.valueOf( datos[3] );
	            	int timeStamp = Integer.valueOf( datos[4] );
	            	c = new Candidato(datos[0], datos[1], salario , nivelEstudio, timeStamp);
	            	
	            	
	            }else if( cadena.equals("Experiencia")){
	            	cadena = b.readLine();
	            	while( !cadena.equals("#") ){
	            		
	            		/*Formato Experiencia
	            		 Cargo;Duracion;Sector*/
	            		datos = cadena.split(";");
	            		int duracion = Integer.parseInt( datos[1] );
	            		Experiencia exp = new Experiencia( datos[0], duracion, datos[2] );
	            		c.addExperencia( exp );

	            		cadena = b.readLine();
	            		
	            	}
	            	
	            	candidatos.add( c );
	            	c = null;
	            }
	        }
	        b.close();
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return candidatos;
	}
}
