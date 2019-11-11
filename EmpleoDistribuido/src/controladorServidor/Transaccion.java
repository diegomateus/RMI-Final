package controladorServidor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class Transaccion implements Serializable, Comparable< Transaccion >{

	private String ID;
	private LocalDateTime timeStamp;
	private Object objeto;
	
	public Transaccion( Object obj ){
		this.setObjeto(obj);
		timeStamp = LocalDateTime.now();
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp= timeStamp;
	}

	@Override
	public int compareTo(Transaccion otherTransaction) {
		// TODO Auto-generated method stub
		return this.timeStamp.compareTo( otherTransaction.getTimeStamp() );
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	
	
}
