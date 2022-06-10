package org.generationitaly.Project.V.controller;

import java.util.List;

import org.generationitaly.Project.V.dao.IDaoPost;
import org.generationitaly.Project.V.entities.Post;
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
@RequestMapping("/post")

public class PostController {
	
		@Autowired
		private IDaoPost dao;

		@GetMapping
		public List<Post> getAll(){
			return dao.post();
		}
		
		@GetMapping("/{id}")
		public Post getOne(@PathVariable int id) {
			return dao.post(id);
		}
		
		@PostMapping
		public void post(@RequestBody Post post) {
			dao.aggiungi(post);
		}
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable int id) {
			dao.elimina(id);
		}

		@PutMapping
		public void update(@RequestBody Post post) {
			dao.modifica(post);
		}
		
}
	