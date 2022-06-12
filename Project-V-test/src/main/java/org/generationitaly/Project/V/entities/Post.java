package org.generationitaly.Project.V.entities;


import java.sql.Timestamp;
import java.util.ArrayList;

public class Post {

	private int idPost;
	private String testo;
	private Timestamp data;
	private int idUtente;
	private ArrayList<Commento> commenti;
	

	public Post(int idPost, String testo, Timestamp data, int idUtente) {
		super();
		this.idPost = idPost;
		this.testo = testo;
		this.data = data;
		this.idUtente = idUtente;
	}

	public int getIdPost() {
		return idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
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
	
	public ArrayList<Commento> getCommenti() {
		return commenti;
	}
	
	public void setCommenti(ArrayList<Commento> commenti) {
		this.commenti = commenti;
	}
	
	
}
