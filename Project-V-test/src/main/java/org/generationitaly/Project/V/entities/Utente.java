package org.generationitaly.Project.V.entities;

public class Utente {
	
	private int idUtente;
	private String nome;
	private String cognome;
	private String numTel;
	private String email;
	private String indirizzo;
	private String CF;
	private String pw;
	
	
	public Utente(int idUtente, String nome, String cognome, String numTel, String email, String indirizzo, String cF, String pw) {
		super();
		this.idUtente = idUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.numTel = numTel;
		this.email = email;
		this.indirizzo = indirizzo;
		this.CF = cF;
		this.pw = pw;
	}
	
	public int getIdUtente() {
		return idUtente;
	}
	
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getNumTel() {
		return numTel;
	}
	
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
	public String getCF() {
		return CF;
	}
	
	public void setCF(String cF) {
		CF = cF;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}


}
