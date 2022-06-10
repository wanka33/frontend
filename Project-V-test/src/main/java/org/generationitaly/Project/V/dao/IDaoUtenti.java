package org.generationitaly.Project.V.dao;

import java.util.List;

import org.generationitaly.Project.V.entities.Utente;

public interface IDaoUtenti {

	List<Utente> utenti();
	
	Utente utente(int id);
	
	boolean aggiungi(Utente u);
	
	boolean elimina(int id);
	
	boolean modifica(Utente u);
	
}
