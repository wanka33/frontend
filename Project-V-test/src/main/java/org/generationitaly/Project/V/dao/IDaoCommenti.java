package org.generationitaly.Project.V.dao;

import java.util.List;

import org.generationitaly.Project.V.entities.Commento;
import org.generationitaly.Project.V.entities.Post;

public interface IDaoCommenti {
	
	List<Commento> commenti(int idPost);

	Commento commento(int id);
	
	boolean aggiungi(Commento c);
	
	boolean elimina(int id);
	
	boolean modifica(Commento c);
	
}
