package com.generation.blogpessoal.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

import jakarta.validation.Valid;

	//Representacional State Transfer
@RestController // classe controladora é queela vai responder a toda requisição que virá pra cá
@RequestMapping ("/postagens")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class PostagemController{
	
	@Autowired
	private PostagemRepository PostagemRepository;

	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(PostagemRepository.findAll());
	}
	//PathVariable pega o id do get e passa para o parametro do metodo Long id
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		
		return PostagemRepository.findById(id)
//dentro da variavel resposta joga o resultado
			.map(resposta ->ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
				//SELECT * FROM tb_postagens WHEREN id =?;
		
	}
	///titulo/= digita pra mim o titulo. {titulo} = o q eu quero procurar.
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(PostagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	
	}
	//insert
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
	 return ResponseEntity.status(HttpStatus.CREATED).body(PostagemRepository.save(postagem));
	 /*INSERT INTO tb_postagens (data, titulo, texto)
	  VALUES (?, ?,)
	  */
	}
	//update
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
	 return ResponseEntity.status(HttpStatus.OK).body(PostagemRepository.save(postagem));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping ("/{id}")
	public void delete(@PathVariable long id) {
		PostagemRepository.deleteById(id);
	
	}
}




