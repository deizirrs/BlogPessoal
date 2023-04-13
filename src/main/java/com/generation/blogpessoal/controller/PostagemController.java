package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

	//Representacional State Transfer
@RestController // classe controladora e que ela vai restponder a toda requisição que vir para cá
@RequestMapping ("/postagens")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class PostagemController{
	
	@Autowired
	private PostagemRepository PostagemRepository;

	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(PostagemRepository.findAll());
				
				//SELECT * FROM tb_postagens
		
	}
}


