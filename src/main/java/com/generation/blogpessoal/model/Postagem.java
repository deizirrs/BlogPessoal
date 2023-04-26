package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//Definir modelo de dados
@Entity
@Table(name = "tb_postagens") /* CREATE TABLE tb_postagens */
public class Postagem {

	// long pq é objeto e não sabemos quantos id teremos
	// Diz que é chave primaria
	@Id
//strategy = fala para o banco de dados gerar o id e retornar o valor "banco de dados, o id é problema seu...só me retorne
//Define que é chave primaria/IDENTITY = Auto_increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/* não nulo, só serve para string*/
	@NotBlank(message = "O Atributo título é Obrigatório!")
	@Size(min = 5, max = 100, message = "O atributo título deve ter no minímo 05 e no máximo 100 caracteres")
	private String titulo;

	@NotBlank(message = "O Atributo texto é Obrigatório!")
	@Size(min = 5, max = 100, message = "O atributo texto deve ter no minímo 10 e no máximo 1000 caracteres")
	private String texto;

	@UpdateTimestamp
	private LocalDateTime data;

//metodos Get e Set, com atributos.
//relacionamentos
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

//criar metodos get e set do objeto tema
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
