/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al candidato que busca una oferta de empleo 
 * @author sistemas
 */

public class Candidato implements Serializable{
    
    private String nombre;
    private String documento;
    private double aspiracionSalarial;
    private Estudio nivelEstudio;
    private List< Experiencia > historial; 
    private int timeStamp;
    
    
    public Candidato( String nombre, String documento, double aspiracionSalarial, Estudio nivelEstudio, int timeStamp){
    	
    	this.nombre = nombre;
    	this.documento = documento;
    	this.aspiracionSalarial = aspiracionSalarial;
    	this.nivelEstudio = nivelEstudio;
    	this.timeStamp = timeStamp;
    	this.historial = new ArrayList<Experiencia>();
    	
    }
    
    public int getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public double getAspiracionSalarial() {
        return aspiracionSalarial;
    }

    public void setAspiracionSalarial(double aspiracionSalarial) {
        this.aspiracionSalarial = aspiracionSalarial;
    }

    public Estudio getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(Estudio nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

	public List<Experiencia> getHistorial() {
		return historial;
	}

	public void setHistorial(List<Experiencia> historial) {
		this.historial = historial;
	}
	
	public void addExperencia( Experiencia e){
		historial.add( e );
	}
    
}
