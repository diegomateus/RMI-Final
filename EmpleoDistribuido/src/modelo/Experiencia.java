/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 * Clase que representa la experiencia laboral de un candidato
 * @author sistemas
 */
public class Experiencia implements Serializable {
    
    private String cargo;
    private int duracion;
    private String sector;

    
    public Experiencia(String cargo, int duracion, String sector) {
		super();
		this.cargo = cargo;
		this.duracion = duracion;
		this.sector = sector;
	}

	public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
    
    
}
