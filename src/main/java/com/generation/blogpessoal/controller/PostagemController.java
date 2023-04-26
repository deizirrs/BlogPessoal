package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;

//Representacional State Transfer
@RestController // classe controladora é queela vai responder a toda requisição que virá pra cá

@RequestMapping("/postagens")
//para habilitar requisiçoes de outras origens, se nao habilitar ele da negado as requisiçoes/allowedHeaders- liberar o caçalhor da requisiçao, para chegar o token
// a aplicaçao http me retorna no cabeçalho o token
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	// injeçao de dependencia
	@Autowired // caraterizar a injeçao de dependencia
	private PostagemRepository PostagemRepository;
	
	@Autowired 
	private TemaRepository TemaRepository;
//traz todo os met para manipular o bd

	// met construtor
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(PostagemRepository.findAll());

		// ok -resposta padrão / findAll -trás todas a postagens
	}
	// PathVariable pega o id do get e passa para o parametro do metodo Long id

	@GetMapping("/{id}") // variavel dentro de chaves {}
	// getById - o objeto nao pode ser nulo/ @PathVariable apenas uma postagem
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		// retorna o resultado do findById
		return PostagemRepository.findById(id)
//dentro da variavel resposta joga o resultado
				.map(resposta -> ResponseEntity.ok(resposta))

				// if-orElse
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		// SELECT * FROM tb_postagens WHEREN id =?;

	}

	/// titulo/= digita pra mim o titulo. {titulo} = o q eu quero procurar.
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(PostagemRepository.findAllByTituloContainingIgnoreCase(titulo));

	}

	@PostMapping // cria/cadastra
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {
		return TemaRepository.findById(postagem.getTema().getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(PostagemRepository.save(postagem)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

		/* INSERT INTO tb_postagens (data, titulo, texto)VALUES (?, ?,) */
	}

	// update
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(PostagemRepository.save(postagem));
	}

	// resposta padrão do metodo NO_CONTENT
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		PostagemRepository.deleteById(id);
		Optional<Postagem> postagem = PostagemRepository.findById(id);
		if (postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		PostagemRepository.deleteById(id);
	}
}
