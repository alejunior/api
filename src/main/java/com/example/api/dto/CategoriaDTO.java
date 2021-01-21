package com.example.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.api.domain.Categoria;

public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotBlank(message = "Nome não pode ser vazio.")
	@Size(min = 5, max = 50, message = "Nome deve ter minimo de 5 e maximo de 50 caracteres.")
	private String nome;
	
	public CategoriaDTO(){
	}

	public CategoriaDTO(Categoria categoria){
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
