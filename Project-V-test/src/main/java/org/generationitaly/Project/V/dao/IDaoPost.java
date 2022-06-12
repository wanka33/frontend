package org.generationitaly.Project.V.dao;

import java.util.List;

import org.generationitaly.Project.V.entities.Post;

public interface IDaoPost {

	List<Post> post();
	
	List<Post> postUtente(int id);
	
	List<Post> postCommentati();
	
	Post post(int id);
	
	boolean aggiungi(Post p);
	
	boolean elimina(int id);
	
	boolean modifica(Post p);
	
}
