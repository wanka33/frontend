package org.generationitaly.Project.V.controller;

import java.util.List;

import org.generationitaly.Project.V.dao.IDaoUtenti;
import org.generationitaly.Project.V.entities.Utente;
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
@RequestMapping("/utenti")
public class UtentiController {
	
	@Autowired
	private IDaoUtenti dao;

	@GetMapping
	public List<Utente> getAll(){
		return dao.utenti();
	}
	
	@GetMapping("/{id}")
	public Utente getOne(@PathVariable int id) { 
		return dao.utente(id);
	}
	
	@PostMapping
	public void post(@RequestBody Utente utente) {
		dao.aggiungi(utente);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		dao.elimina(id);
	}

	@PutMapping
	public void update(@RequestBody Utente utente) {
		dao.modifica(utente);
	}
	
}
