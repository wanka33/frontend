package org.generationitaly.Project.V.controller;

import java.util.List;

import org.generationitaly.Project.V.dao.IDaoCommenti;
import org.generationitaly.Project.V.entities.Commento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commenti")
public class CommentiController {

	@Autowired
	private IDaoCommenti dao;

	@GetMapping("/{idPost}")
	public List<Commento> getAll(@PathVariable int idPost){
		return dao.commenti(idPost);
	}
	
//	@GetMapping("/{id}")
//	public Commento getOne(@PathVariable int id) {
//		return dao.commento(id);
//	}
	
	@PostMapping
	public void post(@RequestBody Commento commento) {
		dao.aggiungi(commento);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		dao.elimina(id);
	}

	@PutMapping
	public void update(@RequestBody Commento commento) {
		dao.modifica(commento);
	}
	
}
