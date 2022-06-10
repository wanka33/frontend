package org.generationitaly.Project.V.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class Commento {
	
	private int idCommento;
	private String testo;
	private Timestamp data;
	private int idUtente;
	private int idPost;
	
	public Commento(int idCommento, String testo, Timestamp data, int idUtente, int idPost) {
		super();
		this.idCommento = idCommento;
		this.testo = testo;
		this.data = data;
		this.idUtente = idUtente;
		this.idPost = idPost;
	}

	public int getIdCommento() {
		return idCommento;
	}

	public void setIdCommento(int idCommento) {
		this.idCommento = idCommento;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public int getIdPost() {
		return idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	
}
	