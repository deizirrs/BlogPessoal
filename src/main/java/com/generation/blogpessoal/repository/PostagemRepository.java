package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.blogpessoal.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	
	//Metodo de consulta
	
//associa a model com a interface /@Param mapear o parametro
	List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
	//vai listar todas a postagens iginorando letras maiusculas e minuculas do titulo
	//SELECT *FROM tb_postagens WHERE titulo LIKE "%titulo%";
}
 