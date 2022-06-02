package com.farmacia.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmacia.model.Categorias;
import com.farmacia.farmacia.repository.CategoriasRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins ="*", allowedHeaders ="*")
public class CategoriasController {
	
	@Autowired
	private CategoriasRepository categoriasRepository;
	
	@GetMapping
	public ResponseEntity<List<Categorias>> getAll(){
		return ResponseEntity.ok(categoriasRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categorias>getById(@PathVariable Long id){
		return categoriasRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/classe/{classe}")
	public ResponseEntity<List<Categorias>>getByClasse(@PathVariable String classe){
		return ResponseEntity.ok(categoriasRepository.findAllByClasseContainingIgnoreCase(classe));
	}
	
	@PostMapping
	public ResponseEntity<Categorias> post(@Valid @RequestBody Categorias categorias){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoriasRepository.save(categorias));
	}
	
	@PutMapping
	public ResponseEntity<Categorias> put(@ Valid @RequestBody Categorias categorias){
		return ResponseEntity.ok(categoriasRepository.save(categorias));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		categoriasRepository.deleteById(id);
	}

}
