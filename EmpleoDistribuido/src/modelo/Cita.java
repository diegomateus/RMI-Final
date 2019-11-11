package modelo;

import java.io.Serializable;

public class Cita implements Serializable {
	
	
	private int puntaje;
	private String IDEmpresa;
	private Candidato candidato;
	private Oferta oferta;
	
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public String getIDEmpresa() {
		return IDEmpresa;
	}
	public void setIDEmpresa(String iDEmpresa) {
		IDEmpresa = iDEmpresa;
	}
	public Candidato getCandidato() {
		return candidato;
	}
	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	
	
}
