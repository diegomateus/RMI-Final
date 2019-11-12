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
 * Clase que representa las empresas registradas en el sistema
 * @author sistemas
 */
public class Empresa implements Serializable{
    
    private String nombre;
    private String nit;
    private ArrayList< Oferta > ofertas;
    private LocalDateTime timeStampLectura;
    private LocalDateTime timeStampEscritura;
    private ArrayList< Empresa >tentativas;
    private Empresa Dseleccionada;

    
    /**
	 * @param nombre
	 * @param nIT
	 */
	public Empresa(String nombre, String nIT, LocalDateTime timeStampLectura, LocalDateTime timeStampEscritura) {
		super();
		this.nombre = nombre;
		this.nit = nIT;
		this.timeStampEscritura = timeStampEscritura;
		this.timeStampLectura = timeStampLectura;
		ofertas = new ArrayList<Oferta>();
		tentativas = new ArrayList<Empresa>();
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

	public LocalDateTime getTimeStampLectura() {
		return timeStampLectura;
	}

	public void setTimeStampLectura(LocalDateTime timeStampLectura) {
		this.timeStampLectura = timeStampLectura;
	}

	public LocalDateTime getTimeStampEscritura() {
		return timeStampEscritura;
	}

	public void setTimeStampEscritura(LocalDateTime timeStampEscritura) {
		this.timeStampEscritura = timeStampEscritura;
	}

	public ArrayList< Empresa > getTentativas() {
		return tentativas;
	}

	public void setTentativas(ArrayList< Empresa > tentativas) {
		this.tentativas = tentativas;
	}

	public Empresa getDseleccionada() {
		return Dseleccionada;
	}

	public void setDseleccionada(Empresa dseleccionada) {
		Dseleccionada = dseleccionada;
	}

	public ArrayList< Oferta > getOfertas() {
		return ofertas;
	}

	public void setOfertas(ArrayList< Oferta > ofertas) {
		this.ofertas = ofertas;
	}

	/**
	 * @return the nIT
	 */
	public String getNIT() {
		return nit;
	}

	/**
	 * @param nIT the nIT to set
	 */
	public void setNIT(String nIT) {
		this.nit = nIT;
	}
	
	public void agregarOferta (Oferta o) {
		this.ofertas.add(o);
	}
}
