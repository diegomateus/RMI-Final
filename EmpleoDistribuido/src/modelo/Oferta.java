/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Clase que representa las ofertas de trabajo
 * @author sistemas
 */
public class Oferta implements Serializable{
    
    private String cargo;
    private int experiencia;
    private double salarioOfrecido;
    private String sectorEmpresa;
    private Estudio nivelEstudio;
    private LocalDateTime timeStampLectura;
    private LocalDateTime timeStampEscritura;
    private ArrayList< Oferta >tentativas;
    private ArrayList<String> citaAsignada; 
    
    private Oferta Dseleccionada;
    
    public Oferta(){
    	
    	super();
    }
    
    public Oferta(String cargo, int experiencia, double salarioOfrecido, String sectorEmpresa, Estudio nivelEstudio,
			LocalDateTime timeStampLectura, LocalDateTime timeStampEscritura) {
		super();
		this.cargo = cargo;
		this.experiencia = experiencia;
		this.salarioOfrecido = salarioOfrecido;
		this.sectorEmpresa = sectorEmpresa;
		this.nivelEstudio = nivelEstudio;
		this.timeStampLectura = timeStampLectura;
		this.timeStampEscritura = timeStampEscritura;
		this.tentativas = new ArrayList<>();
		this.citaAsignada = new ArrayList<>(); 
		this.Dseleccionada = new Oferta();
		this.Dseleccionada.setTimeStampEscritura( LocalDateTime.now() );
	    
		
	}

	public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public double getSalarioOfrecido() {
        return salarioOfrecido;
    }

    public void setSalarioOfrecido(double salarioOfrecido) {
        this.salarioOfrecido = salarioOfrecido;
    }

    public String getSectorEmpresa() {
        return sectorEmpresa;
    }

    public void setSectorEmpresa(String sectorEmpresa) {
        this.sectorEmpresa = sectorEmpresa;
    }

	public Oferta getDseleccionada() {
		return Dseleccionada;
	}

	public void setDseleccionada(Oferta dseleccionada) {
		Dseleccionada = dseleccionada;
	}

	public LocalDateTime getTimeStampEscritura() {
		return timeStampEscritura;
	}

	public void setTimeStampEscritura(LocalDateTime timeStampEscritura) {
		this.timeStampEscritura = timeStampEscritura;
	}

	public LocalDateTime getTimeStampLectura() {
		return timeStampLectura;
	}

	public void setTimeStampLectura(LocalDateTime timeStampLectura) {
		this.timeStampLectura = timeStampLectura;
	}

	public Estudio getNivelEstudio() {
		return nivelEstudio;
	}

	public void setNivelEstudio(Estudio nivelEstudio) {
		this.nivelEstudio = nivelEstudio;
	}
	
	public ArrayList<Oferta> getTentativas() {
		return tentativas;
	}

	public void setTentativas(ArrayList<Oferta> tentativas) {
		this.tentativas = tentativas;
	}

	public ArrayList<String> getCitaAsignada() {
		return citaAsignada;
	}

	public void setCitaAsignada(ArrayList<String> citaAsignada) {
		this.citaAsignada = citaAsignada;
	}

	public synchronized void addTentativa( Oferta tentativa){
		this.tentativas.add( tentativa );
	}
	
	public synchronized void addCitaAsignada( String nombre){
		this.citaAsignada.add( nombre );
	}
    
}
